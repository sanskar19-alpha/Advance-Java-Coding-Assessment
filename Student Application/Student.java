public class Student {
    private String id;
    private String name;
    private int age;
    private String branch;
    private String gender;

    public Student() {
    }

    public Student(String id, String name, int age, String branch, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.branch = branch;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
