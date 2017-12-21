package pl.edu.agh.ki.bd2;

public class Solution {

    private final GraphDatabase graphDatabase = GraphDatabase.createDatabase();

    public void databaseStatistics() {
        System.out.println(graphDatabase.runCypher("CALL db.labels()"));
        System.out.println(graphDatabase.runCypher("CALL db.relationshipTypes()"));
    }


    public void runAllTests() {
        System.out.println(findActorByName("Emma Watson"));
        System.out.println(findMovieByTitleLike("Star Wars"));
        System.out.println(findRatedMoviesForUser("maheshksp"));

        System.out.println(findMovieRecommendationForUser("emileifrem"));
    }

    private String findActorByName(final String actorName) {
        final String query = String.format(
                "match(p: Person) " +
                "where p.name contains \"%s\" return p.name limit 1", actorName);
        return graphDatabase.runCypher(query);
    }

    private String findMovieByTitleLike(final String movieName) {
        final String query = String.format(
                "match(m: Movie) " +
                        "where m.title contains \"%s\" return m.title limit 1", movieName);
        return graphDatabase.runCypher(query);
    }

    private String findRatedMoviesForUser(final String userLogin) {
        final String query = String.format(
                "match((u: User{login:\"%s\"}) -[:RATED]-> (m:Movie))" +
                        "return m.title limit 100",userLogin);
        return graphDatabase.runCypher(query);
    }



    private String findMovieRecommendationForUser(final String userLogin) {
        return null;
    }

}
