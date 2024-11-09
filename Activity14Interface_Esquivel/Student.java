package Activity14Interface_Esquivel;

public class Student extends Teacher {
    @Override
    public void listen() {
        System.out.println("I am listening!");
    }

    @Override
    public void teach() {
        System.out.println("I am teaching!");
    }

    @Override
    public void learn() {
        System.out.println("I am learning!");
    }

    @Override
    public void know() {
        System.out.println("I am knowing!");
    }

    @Override
    public void look() {
        System.out.println("I am looking!");
    }
}
