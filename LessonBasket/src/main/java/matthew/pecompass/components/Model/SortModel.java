package matthew.pecompass.components.Model;

/**
 * Created by Sihua on 2016/12/1.
 */

public class SortModel implements Comparable {
    private String name;   //显示的数据
    private String sortLetters;  //显示数据拼音的首字母

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSortLetters() {
        return sortLetters;
    }
    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    public SortModel(String name, String sortLetters) {
        this.name = name;
        this.sortLetters = sortLetters;
    }

    @Override
    public int compareTo(Object another) {
        SortModel ano=(SortModel) another;
        return this.name.charAt(0)-ano.name.charAt(0);
    }
}
