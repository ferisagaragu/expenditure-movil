package javabrain.org.expenditure.persistence;

import java.util.ArrayList;
import java.util.List;

import javabrain.org.expenditure.pojo.Expenditure;
import javabrain.org.expenditure.pojo.Month;
import javabrain.org.expenditure.pojo.User;

/**
 * Created by Fernando García on 01/04/2019.
 */

public class Global {
    public static User user = new User();
    public static Month month = new Month();
    public static List<Expenditure> expenditures = new ArrayList<>();
}
