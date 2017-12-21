package pl.edu.agh.ki.bd2;

import pl.edu.agh.ki.bd2.model.Dao;
import pl.edu.agh.ki.bd2.model.Student;
import pl.edu.agh.ki.bd2.model.Subject;
import pl.edu.agh.ki.bd2.model.Teacher;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    final static int stCnt = 20;
    final static int tchCnt = 5;
    final static int subCnt = 5;

    final static int knowsCnt = 10;
    final static int teachedByCnt = 10;
    final static int patricipatesCnt = 40;

    public static String randString(){
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        while(sb.toString().length() < 8){
            sb.append((char)(r.nextInt(26) + 'a'));
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Student> studentList = new ArrayList<>();
        ArrayList<Teacher> teachers = new ArrayList<>();
        ArrayList<Subject> subjects  = new ArrayList<>();
        Dao dao = new Dao();
        dao.truncate();



        for(int i= 0; i<stCnt; i++){
            Student student = new Student(randString(), randString(),randString());
            studentList.add(student);
            dao.persistStudent(student);
        }

        for(int i= 0; i<tchCnt; i++){
            Teacher teacher = new Teacher(randString(),randString());
            teachers.add(teacher);
            dao.persistTeacher(teacher);
        }

        for(int i= 0; i<subCnt; i++){
            Subject subject = new Subject(randString(),randString());
            subjects.add(subject);
            dao.persistSubject(subject);
        }
        //relation KNOWS
        int i = 0;
        Random random = new Random();
        while (i<knowsCnt){
            int s1 = random.nextInt(studentList.size());
            int s2 = random.nextInt(studentList.size());
            while(s1==s2)
                s2 = random.nextInt(studentList.size());
            dao.createKnows(studentList.get(s1), studentList.get(s2));
            i++;

        }

        while (i<teachedByCnt){
            int t = random.nextInt(teachers.size());
            int s = random.nextInt(subjects.size());

            dao.createTaughtBy(subjects.get(s), teachers.get(t));
            i++;
        }

        while(i<patricipatesCnt){
            Subject sub = subjects.get(random.nextInt(subjects.size()));
            Student std =studentList.get(random.nextInt(studentList.size()));
            i++;
            dao.createParticipates(std,sub);
        }
        dao.databaseStatistics();
        testFindAllRelation(dao);
        testPath(dao);

    }
    private static void testPath(Dao dao){
        Student studenta = new Student("TEST", "RELACJE", "1182831");
        Student studentb = new Student("TEST1", "RELACJE", "1182832");
        Student studentc = new Student("TEST2", "RELACJE", "1182833");
        Student studentd = new Student("TEST3", "RELACJE", "1182834");
        Student studente = new Student("TEST4", "RELACJE", "1182835");
        dao.persistStudent(studenta);
        dao.persistStudent(studentb);
        dao.persistStudent(studentc);
        dao.persistStudent(studentd);
        dao.persistStudent(studente);
        dao.createKnows(studenta, studentb);
        dao.createKnows(studentb, studentc);
        dao.createKnows(studentc, studentd);
        dao.createKnows(studentd, studente);
        System.out.println(dao.findPathsFor2Students(studenta, studente));
    }
    private static void testFindAllRelation(Dao dao) {
        Student studenta = new Student("TEST", "RELACJE", "118283");
        Student studentb = new Student("TEST1", "RELACJE1", "1312");
        dao.persistStudent(studenta);
        dao.persistStudent(studentb);
        dao.createKnows(studenta, studentb);
        Teacher teacher = new Teacher("Teacher_TEST", "TEACHER_TEST");
        dao.persistTeacher(teacher);
        Subject subject = new Subject("SUB_TEST", "2017");
        dao.persistSubject(subject);
        dao.createTaughtBy(subject,teacher);
        dao.createParticipates(studentb,subject);

        System.out.println(String.format("STUDENT A: %s", dao.getAllRelation(studenta) ));
        System.out.println(String.format("STUDENT B: %s", dao.getAllRelation(studentb) ));
        System.out.println(String.format("SUBJECT: %s", dao.getAllRelation(subject) ));
        System.out.println(String.format("TEACHER: %s", dao.getAllRelation(teacher) ));

    }

}
