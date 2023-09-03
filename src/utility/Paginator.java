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
    public int currentPage;
    public int pageNumber;

    public Paginator() {
    }

    public Paginator(ListInterface<T> dataList, int pageSize) {

        currentPage = 0;

        // Calculate the number of pages
        int numPages = (int) Math.ceil((double) dataList.getNumberOfEntries() / pageSize);
        this.pageNumber = numPages;

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

    public ListInterface<T> nextPage() {
        if (isEndOfPage()) {
            System.err.println("You had reached last page.");
            return null;
        }
        return pages.getEntry(++currentPage);
    }

    public ListInterface<T> prevPage() {
        if (currentPage == 0) {
            System.err.println("You are in first page");
            return null;
        }
        return pages.getEntry(--currentPage);
    }

    public ListInterface<T> jumpTo(int index) {
        if (!isValidRange(index)) {
            System.err.println("No such page.");
            return null;
        }
        currentPage = index;
        return pages.getEntry(index);
    }

    public ListInterface<T> toStart() {
        currentPage = 0;
        return pages.getEntry(currentPage);
    }

    public ListInterface<T> toEnd() {
        currentPage = pageNumber - 1;
        return pages.getEntry(currentPage);
    }

    public boolean isEndOfPage() {
        return currentPage == pageNumber - 1;
    }

    public boolean isValidRange(int index) {
        return index >= 0 && index < pageNumber;
    }
}
