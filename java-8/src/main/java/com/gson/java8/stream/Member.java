package com.gson.java8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Member {

    private String level;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }


    public static void main(String[] args) {

        Member member1 = new Member();
        member1.setLevel("1");

        Member member2 = new Member();
        member2.setLevel(null);

        List<Member> memberList = new ArrayList<>();
        memberList.add(member1);
        memberList.add(member2);

        List<String> levels = memberList.stream().map(Member::getLevel).collect(Collectors.toList());
        System.out.println(levels);

        System.out.println(levels.contains("3"));

        System.out.println("-------");
        System.out.println(Boolean.valueOf(null));
        System.out.println(Boolean.valueOf("false"));
        System.out.println(Boolean.valueOf("true"));


    }
}
