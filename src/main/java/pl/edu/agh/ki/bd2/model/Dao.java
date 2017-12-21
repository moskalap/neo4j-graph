package pl.edu.agh.ki.bd2.model;

import pl.edu.agh.ki.bd2.GraphDatabase;

public class Dao {
    private final GraphDatabase graphDatabase = GraphDatabase.createDatabase();

    public void databaseStatistics() {
        System.out.println(graphDatabase.runCypher("CALL db.labels()"));
        System.out.println(graphDatabase.runCypher("CALL db.relationshipTypes()"));
        System.out.println(graphDatabase.runCypher("CALL db.schema()"));
    }

    public void persistStudent(Student student) {
        final String query = String.format("CREATE (n:Student { name: '%s', surname: '%s', pesel: '%s' })",
                student.getStudentName(), student.getStudentSurname(), student.getPesel());
        graphDatabase.runCypher(query);
    }

    public void persistTeacher(Teacher teacher) {
        final String query = String.format("CREATE (n:Teacher { name: '%s', surname: '%s'})",
                teacher.getName(), teacher.getSurname());
        graphDatabase.runCypher(query);
    }

    public void persistSubject(Subject subject) {
        final String query = String.format("CREATE (n:Subject { name: '%s', courseYear: '%s'})",
                subject.getSubjectName(), subject.getCourseYear());
        graphDatabase.runCypher(query);
    }

    public void truncate() {
        final String query = "MATCH (n)\n" +
                "OPTIONAL MATCH (n)-[r]-()\n" +
                "DELETE n,r";
        graphDatabase.runCypher(query);
    }

    public void createTaughtBy(Subject subject, Teacher teacher) {
        final String query = "MATCH (s:Subject),(t:Teacher)\n" +
                "WHERE s.name = '%s' AND t.name = '%s' AND t.surname = '%s'" +
                "CREATE (s)-[r:TAUGHT_BY]->(t)\n" +
                "RETURN r";
        graphDatabase.runCypher(String.format(query, subject.getSubjectName(), teacher.getName(), teacher.getSurname()));


    }

    public void createKnows(Student studentA, Student studentB) {
        final String query = "MATCH (a:Student),(b:Student)\n" +
                "WHERE a.pesel = '%s' AND b.pesel = '%s' " +
                "CREATE (a)-[r:KNOWS]->(b)\n" +
                "RETURN r";
        final String query1 = "MATCH (a:Student),(b:Student)\n" +
                "WHERE a.pesel = '%s' AND b.pesel = '%s' " +
                "CREATE (a)<-[r:KNOWS]-(b)\n" +
                "RETURN r";
        graphDatabase.runCypher(String.format(query, studentA.getPesel(), studentB.getPesel()));
        graphDatabase.runCypher(String.format(query1, studentA.getPesel(), studentB.getPesel()));

    }

    public void createParticipates(Student student, Subject subject) {
        final String query = "MATCH (a:Student),(b:Subject)\n" +
                "WHERE a.pesel = '%s' AND b.name = '%s' " +
                "CREATE (a)-[r:PARTICIPATES]->(b)\n" +
                "RETURN r";
        graphDatabase.runCypher(String.format(query, student.getPesel(), subject.getSubjectName()));
    }

    public String getAllRelation(Subject subject){
        final String query = "MATCH (:Subject {name: '%s'})-[r]-()\n" +
                "RETURN r";
        return graphDatabase.runCypher(String.format(query,subject.getSubjectName()));
    }
    public String getAllRelation(Student student){
        final String query = "MATCH (:Student {pesel: '%s'})-[r]-()\n" +
                "RETURN r";
        return graphDatabase.runCypher(String.format(query,student.getPesel()));
    }
    public String getAllRelation(Teacher teacher){
        final String query = "MATCH (:Teacher {name: '%s', surname: '%s'})-[r]-()\n" +
                "RETURN r";
        return graphDatabase.runCypher(String.format(query,teacher.getName(), teacher.getSurname()));
    }
        public String findPathsFor2Students(Student st1,Student st2){
            final String query = "MATCH (s1:Student {pesel: '%s'})" +
                    "-[r:KNOWS*1..100]-(s2:Student  {pesel: '%s'})\n" +
                    "RETURN r";
            return graphDatabase.runCypher(String.format(query, st1.getPesel(), st2.getPesel()));
        }

}
