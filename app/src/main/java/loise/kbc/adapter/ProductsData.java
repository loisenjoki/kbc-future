package loise.kbc.adapter;

/**
 * Created by v on 10-Feb-15.
 */
public class ProductsData {
    String name;
    String description;
    String moredesc;
    String price;

    int image;

    int id_;


    public ProductsData(String name, String description,String moredesc, String price, int image, int id_){

        this.name = name;
        this.description = description;
        this.moredesc = moredesc;
        this.price = price;
        this.image = image;
        this.id_ = id_;
    }

 public String getName(){
     return name;
 }


    public String getDescription(){
        return description;
    }

    public String getMoreData(){
        return moredesc;
    }


    public String getPrice(){
        return price;
    }


    public int getImage(){
        return image;
    }



    public int getId(){
        return id_;
    }

}
