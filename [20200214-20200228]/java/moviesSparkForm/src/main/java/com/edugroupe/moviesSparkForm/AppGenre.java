package com.edugroupe.moviesSparkForm;

import java.io.Serializable;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;


public class AppGenre 
{
	
	public static class Rating implements Serializable {
		public int userId;
		public int movieId;
		public double rating;
		public String timestamp;
		
		@Override
		public String toString() {
			return "Rating [userId=" + userId + ", movieId=" + movieId + ", rating=" + rating + ", timestamp="
					+ timestamp + "]";
		}

		public Rating(int userId, int movieId, double rating, String timestamp) {
			this.userId = userId;
			this.movieId = movieId;
			this.rating = rating;
			this.timestamp = timestamp;
		}
		
		public static Rating fromCsvLine(String line) {
			String[] champs = line.split(",");
			return new Rating(	Integer.parseInt(champs[0]),
								Integer.parseInt(champs[1]),
								Double.parseDouble(champs[2]),
								champs[3]);
		}
	}
	
	
	public static class Movie implements Serializable {
		public int movieId;
		public String title;
		public String[] genres;
		
		@Override
		public String toString() {
			return "Movie [movieId=" + movieId + ", title=" + title + ", genres=" + Arrays.toString(genres) + "]";
		}

		public Movie(int movieId, String title, String[] genres) {
			this.movieId = movieId;
			this.title = title;
			this.genres = genres;
		}

		public static Movie fromCsvLine(String line) {
			int posFirst = line.indexOf(','); // position premiere virgule, après premier champ
			int posLast = line.lastIndexOf(','); // position derniere virgule, début dernier champs
			String identifiant = line.substring(0, posFirst);
			String title = line.substring(posFirst + 1, posLast).replaceAll("\"", "");
			String [] genres = line.substring(posLast+1).split("[|]");
			return new Movie(Integer.parseInt(identifiant), title, genres);
		}
		
	}
	
    public static void main( String[] args )
    {
       SparkConf conf = new SparkConf().setAppName("MovieSpark")
    		   							.setMaster("local[*]");
       
       JavaSparkContext sc = new JavaSparkContext(conf);
       
       JavaRDD<String> movieLines =
    		   sc.textFile("/user/formation/moviedata/moviessmallinput/movies.csv")
    		   		.filter(l -> !l.startsWith("movieId"));
       
       JavaRDD<String> ratingLines =
    		   sc.textFile("/user/formation/moviedata/ratingssmallinput/ratings.csv")
    		   		.filter(l -> !l.startsWith("userId"));
       
       
       JavaPairRDD<Integer, Rating> ratings = 
    		   ratingLines.map(l -> Rating.fromCsvLine(l))
    		   .mapToPair(r -> new Tuple2<Integer, Rating>(r.movieId, r));
     
       
       
       JavaPairRDD<Integer, Movie> movies =
    		   movieLines.map(l -> Movie.fromCsvLine(l))
    		   	.mapToPair(m -> new Tuple2<Integer, Movie>(m.movieId, m));
       
     
       JavaPairRDD<Integer, Tuple2<Rating, Movie>> jointure =
    		   ratings.join(movies);
       
       JavaPairRDD<String, Tuple2<Double, Integer>> reduction =
    		   jointure.flatMapToPair(
    		j -> Arrays.stream(j._2._2.genres)
    					.map(g -> new Tuple2<String,Tuple2<Double, Integer>>(g, 
    							new Tuple2<Double, Integer>(j._2._1.rating, 1)))
    					.collect(Collectors.toList()))
       				.reduceByKey((mrA, mrB) -> new Tuple2<Double, Integer>(
       							mrA._1 + mrB._1,
       							mrA._2 + mrB._2));

       
       JavaPairRDD<String, Double> moyennes = 
    		   reduction.mapToPair(r -> new Tuple2<String, Double>(r._1, r._2._1 / r._2._2));
       
       moyennes.saveAsTextFile("/user/formation/moviedata/moyennegenres1");
       sc.close();
    }
}
