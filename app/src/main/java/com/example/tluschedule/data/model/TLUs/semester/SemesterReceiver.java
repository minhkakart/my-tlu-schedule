package com.example.tluschedule.data.model.TLUs.semester;

import com.example.tluschedule.data.model.TLUs.JsonModelBase;

import java.util.List;

public class SemesterReceiver extends JsonModelBase {
    private List<SemesterContent> content;
    private int totalElements;
    private int totalPages;
    private boolean last;
    private int size;
    private int number;
    private int numberOfElements;
    private boolean first;

    public SemesterReceiver(List<SemesterContent> content, int totalElements, int totalPages, boolean last, int size, int number, int numberOfElements, boolean first) {
        this.content = content;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
        this.size = size;
        this.number = number;
        this.numberOfElements = numberOfElements;
        this.first = first;
    }

    public List<SemesterContent> getContent() {
        return content;
    }

    public void setContent(List<SemesterContent> content) {
        this.content = content;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public String toJsonString(){
        return "{" +
                "\"content\":" + toJsonArrayString(content) +
                ", \"totalElements\":" + totalElements +
                ", \"totalPages\":" + totalPages +
                ", \"last\":" + last +
                ", \"size\":" + size +
                ", \"number\":" + number +
                ", \"numberOfElements\":" + numberOfElements +
                ", \"first\":" + first +
                '}';
    }
}
