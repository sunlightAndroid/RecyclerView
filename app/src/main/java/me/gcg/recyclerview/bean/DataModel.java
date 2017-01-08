package me.gcg.recyclerview.bean;

/**
 * Created by imSunLight on 2016/12/26.
 * 邮箱:1944633835@qq.com
 */
public class DataModel {

    public  static   final  int  TYPE_ONE=1;
    public static    final  int TYPE_TWO=2;

    private  int dataType;

    private  String title;
    private String descreption;

    public DataModel(String title, String descreption, int dataType) {
        this.title = title;
        this.descreption = descreption;
        this.dataType = dataType;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }

    @Override
    public String toString() {
        return "DataModel{" +
                "TYPE_ONE=" + TYPE_ONE +
                ", TYPE_TWO=" + TYPE_TWO +
                ", dataType=" + dataType +
                ", title='" + title + '\'' +
                ", descreption='" + descreption + '\'' +
                '}';
    }
}
