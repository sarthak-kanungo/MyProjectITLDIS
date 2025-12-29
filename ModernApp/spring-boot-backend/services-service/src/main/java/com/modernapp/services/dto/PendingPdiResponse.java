package com.modernapp.services.dto;

import java.util.List;

public class PendingPdiResponse {
    private List<PendingChassisDto> content;
    private long totalElements;
    private int totalPages;
    private int currentPage;
    private int size;

    public PendingPdiResponse() {
    }

    public PendingPdiResponse(List<PendingChassisDto> content, long totalElements, 
                             int totalPages, int currentPage, int size) {
        this.content = content;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.size = size;
    }

    // Getters and Setters
    public List<PendingChassisDto> getContent() {
        return content;
    }

    public void setContent(List<PendingChassisDto> content) {
        this.content = content;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

