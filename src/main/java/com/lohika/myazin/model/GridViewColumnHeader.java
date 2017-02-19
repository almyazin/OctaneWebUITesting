package com.lohika.myazin.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by amyazin on 2/13/2017.
 */
public class GridViewColumnHeader extends BaseWebElement {
    //public SortingOrder sortingOrder = SortingOrder.UNSORTED;
    public String name;
    //public boolean isSorted = false;

    public GridViewColumnHeader(final WebElement element) {
        super(element);
        name = getColumnName();
    }

    public String getColumnName(){
        //return decoratedElement.findElement(By.xpath("./span[@class='slick-column-name']")).getText();
        return decoratedElement.getAttribute("title");
    }

    public boolean isColumnSorted(){
        return decoratedElement.getAttribute("class").contains("slick-header-column-sorted");
    }

    public SortingOrder getColumnSortingOrder(){
        if (!isColumnSortable())
            return SortingOrder.UNSORTED;
        String sortIndicatorClass = decoratedElement.findElement(By.xpath("./span[contains(@class, 'slick-sort-indicator')]")).getAttribute("class");
        return sortIndicatorClass.contains("slick-sort-indicator-asc")
                ? SortingOrder.ASCENDING : sortIndicatorClass.contains("slick-sort-indicator-desc")
                ? SortingOrder.DESCENDING :  SortingOrder.UNSORTED;
    }

    public boolean isColumnSortable(){
        return decoratedElement.getAttribute("class").contains("slick-header-sortable");
    }
}
