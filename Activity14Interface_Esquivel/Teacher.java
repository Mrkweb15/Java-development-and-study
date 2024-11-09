package Activity14Interface_Esquivel;

public abstract class Teacher implements Readable {
    public String name = "mr. K";
    public int age = 26;

    @Override
    public abstract void teach();
}
