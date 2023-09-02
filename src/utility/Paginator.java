/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import adt.CircularDoublyLinkedList;
import adt.ListInterface;

/**
 *
 * @author Yip Zi Yan
 *
 */
public class Paginator<T> {

    public ListInterface<ListInterface<T>> pages;
    private int currentPage;
    private int pageSize;

    public Paginator() {
    }

    public Paginator(ListInterface<T> dataList, int pageSize) {
        this.pageSize = pageSize;
        currentPage = 0;

        // Calculate the number of pages
        int numPages = (int) Math.ceil((double) dataList.getNumberOfEntries() / pageSize);

        // Initialize the pages
        pages = new CircularDoublyLinkedList<>();
        int startIndex = 0;
        int endIndex = pageSize - 1;

        for (int i = 0; i < numPages; i++) {
            pages.add(dataList.subList(startIndex, endIndex));
            startIndex = endIndex + 1;
            endIndex = Math.min(startIndex + pageSize - 1, dataList.getNumberOfEntries() - 1);
            
        }
    }

}
