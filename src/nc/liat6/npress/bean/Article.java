package nc.liat6.npress.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章
 *
 * @author 6tail
 *
 */
public class Article{

  /** ID */
  private long id;
  /** 标题 */
  private String title;
  /** 内容 */
  private String content;
  /** 简要描述 */
  private String description;
  /** 发布日期 */
  private String day;
  /** 发布时间 */
  private String time;
  /** 图片路径 */
  private String pic;
  /** 分类 */
  private List<Cat> cats = new ArrayList<Cat>();
  /** 标签 */
  private List<Cat> tags = new ArrayList<Cat>();

  public long getId(){
    return id;
  }

  public void setId(long id){
    this.id = id;
  }

  public String getTitle(){
    return title;
  }

  public void setTitle(String title){
    this.title = title;
  }

  public String getContent(){
    return content;
  }

  public void setContent(String content){
    this.content = content;
  }

  public String getDescription(){
    return description;
  }

  public void setDescription(String description){
    this.description = description;
  }

  public String getDay(){
    return day;
  }

  public void setDay(String day){
    this.day = day;
  }

  public String getTime(){
    return time;
  }

  public void setTime(String time){
    this.time = time;
  }

  public String getPic(){
    return pic;
  }

  public void setPic(String pic){
    this.pic = pic;
  }

  public List<Cat> getCats(){
    return cats;
  }

  public void setCats(List<Cat> cats){
    this.cats = cats;
  }

  public void addCat(Cat cat){
    cats.add(cat);
  }

  public List<Cat> getTags(){
    return tags;
  }

  public void setTags(List<Cat> tags){
    this.tags = tags;
  }

  public void addTag(Cat tag){
    tags.add(tag);
  }
}
