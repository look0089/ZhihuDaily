package org.jzs.mybaseapp.section.my.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jzs on 2017/9/14 0014.
 */

public class PersonInfo {
    public String name;
    public String location;
    public String tel;
    public String email;
    public String school;
    public String work_experience;
    public String education;
    public String major;
    public String planning;
    public String self_evaluation;
    public String interest;
    public List<String> skills = new ArrayList<>();
    public List<Experience> experience = new ArrayList<>();
}
