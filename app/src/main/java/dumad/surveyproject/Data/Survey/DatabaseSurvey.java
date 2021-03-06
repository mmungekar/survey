package dumad.surveyproject.Data.Survey;

import java.util.Map;

import dumad.surveyproject.Data.Item.Item;
import dumad.surveyproject.Deserializable;

/**
 * Created by Ritler on 10/28/17.
 * Survey POJO model.
 */

public class DatabaseSurvey implements Deserializable<Survey> {

    Map<String, Object> questions;
    Map<String, String> orders;
    String id, location;

    DatabaseSurvey() {

    }

    public Map<String, String> getOrders() {
        return orders;
    }

    public String getLocation() {
        return location;
    }

    public String getId() {
        return id;
    }

    public Map<String, Object> getQuestions() {
        return questions;
    }

    @Override
    public Survey deserialize() {
        Survey s = new Survey(id, location);
        if(questions == null)
            return s;

        for(String str : questions.keySet()) {

            Map<String, Object> map = (Map<String, Object>) questions.get(str);

            Double carbs = (Double) map.get("carbs");
            Double calories = (Double) map.get("calories");
            Double fats = (Double) map.get("fats");
            Double proteins = (Double) map.get("proteins");
            Double price = (Double) map.get("price");
            String description = (String) map.get("description");
            String name = (String) map.get("name");

            Item i = new Item(carbs, calories, fats, proteins, price, description, name);
            s.addItem(i);
        }

        if(this.orders == null)
            return s;

        for(String str : this.orders.keySet()) {
            s.addOrder(str, this.orders.get(str));
        }

        return s;
    }
}
