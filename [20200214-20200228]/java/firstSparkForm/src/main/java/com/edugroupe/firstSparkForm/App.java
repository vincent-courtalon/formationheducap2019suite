package com.edugroupe.firstSparkForm;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "démarrage spark!" );
        
        SparkConf conf = new SparkConf().setAppName("wordCountSpark")
        								.setMaster("local[*]");
        // master sert a indiquer le noeud "maitre"
        // ici, local indique localhost
        // [*] -> indiquer le parallelisme a utiliser, ici, * lui laisse choisir
        
        // contexte d'execution spark
        JavaSparkContext sc = new JavaSparkContext(conf);
        
        /*
         * le fonctionnement de spark est centré autour de RDD
         * un des principe fondamentaux est l'évaluation paresseuse
         * on va construire un "graphe" d'execution via des transformations de RDD
         *   
         * l'execution n'aura vraiment lieu que lors de la demande de sortie d'un resultat
         * 
         */
        
        // permière étape, lire les données depuis une entrée, ici un livre dans hdfs
        JavaRDD<String> lines = sc.textFile("/user/formation/wordcountexo/input/54873-0.txt");
        
        // si je veux compter les mots, je vais avoir en sortie
        // String -> Integer (Tuple2)
        
        JavaPairRDD<String, Integer> resultat =
        		// split des lignes
        		// map 1 entree --> 1 sortie
        		// flatmap  1 entree -> tableau -> elem1,elem2
        		// split genere un tableau -> on a un ensemble de tableau à la suite
        		// les uns des autres ["le", "matin", "pizza"] , ["le", "soir", "sushi"], ...
        		// flatMap lit le contenu de ces tableau, et ressort un seul tableau
        		// flux écrasé ["le", "matin", "pizza", "le", "soir", "sushi"....]
        		lines.flatMap(l -> Arrays.asList(l.split("[- .,;!?'\"$#]+")))
        			 .mapToPair(mot -> new Tuple2<String, Integer>(mot, 1))
        			 .reduceByKey((compte1, compte2) -> compte1 + compte2);
        			
        // c'est une action -> déclenchement du traitement
        resultat.saveAsTextFile("/user/formation/wordcountspark1");
        
        sc.close();
     }
}
