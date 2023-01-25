package hello.hellospring.controller;

public class MemberForm {
    private String name;
// MembersFome.html 에 있는 name과
//    값이 매칭이 되서 들어 올 것이다.
    public void setName(String name){
    this.name = name;
}

    public String getName(){
        return name;
    }

}
