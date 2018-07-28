package com.mikewoo.study.concurrency.example.lock;

/**
 * @author Eric Gui
 * @date 2018/7/28
 */
public class Data {
    private Long id;

    private String data;

    public Data(Long id, String data) {
        this.id = id;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", data='" + data + '\'' +
                '}';
    }
}
