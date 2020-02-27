package com.edugroupe.moviesSparkForm;

import java.io.Serializable;
import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple4;


public class AppTag 
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
	
	public static class Tag implements Serializable {
		public int userId;
		public int movieId;
		public String tag;
		public String timestamp;
		
		public Tag(int userId, int movieId, String tag, String timestamp) {
			this.userId = userId;
			this.movieId = movieId;
			this.tag = tag;
			this.timestamp = timestamp;
		}
		@Override
		public String toString() {
			return "Tag [userId=" + userId + ", movieId=" + movieId + ", tag=" + tag + ", timestamp=" + timestamp + "]";
		}
		
		public static Tag fromCsvLine(String line) {
			String[] champs = line.split(",");
				return new Tag(	Integer.parseInt(champs[0]),
								Integer.parseInt(champs[1]),
								champs[2],
								champs[3]);
		}
		
	}
	
    public static void main( String[] args )
    {
       SparkConf conf = new SparkConf().setAppName("MovieSpark")
    		   							.setMaster("local[*]");
       
       JavaSparkContext sc = new JavaSparkContext(conf);
       
       /*JavaRDD<String> movieLines =
    		   sc.textFile("/user/formation/moviedata/moviessmallinput/movies.csv")
    		   		.filter(l -> !l.startsWith("movieId"));
       */
       
       JavaRDD<String> tagLines = 
    		   sc.textFile("/user/formation/moviedata/tagssmallinput/tags.csv")
    		   		.filter(l -> !l.startsWith("userId"));
       
       JavaRDD<String> ratingLines =
    		   sc.textFile("/user/formation/moviedata/ratingssmallinput/ratings.csv")
    		   		.filter(l -> !l.startsWith("userId"));
       
      
       
       JavaPairRDD<Integer, Rating> ratings = 
    		   ratingLines.map(l -> Rating.fromCsvLine(l))
    		   .mapToPair(r -> new Tuple2<Integer, Rating>(r.movieId, r));
     
       
       
       JavaPairRDD<Integer, Tag> tags =
    		   tagLines.map(l -> Tag.fromCsvLine(l))
    		   	.mapToPair(t -> new Tuple2<Integer, Tag>(t.movieId, t));
       
       
       
       JavaPairRDD<Integer, Tuple2<Rating, Tag>> jointure =
    		   ratings.join(tags);
       
       
       
       //jointure.take(200).forEach(r -> System.out.println(r));
       
       
       JavaPairRDD<String, Tuple4<Integer, Double, Double, Double>> reduction =
    		   jointure.mapToPair(
    		j -> new Tuple2<String, Tuple4<Integer, Double, Double, Double>>(
    				   j._2._2.tag,
    				   new Tuple4<Integer, Double, Double, Double>
    				   			  (1,j._2._1.rating,j._2._1.rating, j._2._1.rating)
    				   ))
    		   .reduceByKey((mrA, mrB) -> new Tuple4<Integer, Double, Double, Double>(
    				   					mrA._1() + mrB._1(),	// compteur
    				   					mrA._2() + mrB._2(),	// somme rating
    				   					Math.min(mrA._3(), mrB._3()), // plus petit tating
    				   					Math.max(mrA._4(), mrB._4()))); // plus grand rating
       
       // reduction.take(500).forEach(r -> System.out.println(r));
       
       
       
       JavaPairRDD<String, Tuple3<Double, Double, Double>> moyennes = 
    		   reduction.filter(r -> r._2._1() >= 3)
    		   			.mapToPair(r -> new Tuple2<String, Tuple3<Double, Double, Double>>(
    		   				r._1,
    		   				new Tuple3<Double, Double, Double>(r._2._2() / r._2._1(), r._2._3(), r._2._4())));
       
       moyennes.saveAsTextFile("/user/formation/moviedata/moyennetags1");
     
       sc.close();
    }
}
