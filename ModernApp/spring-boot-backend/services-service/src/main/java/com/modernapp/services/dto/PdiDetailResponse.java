package com.modernapp.services.dto;

import java.util.List;

public class PdiDetailResponse {
    private List<PdiDetailDto> content;
    private long totalElements;
    private int totalPages;
    private int currentPage;
    private int size;

    public PdiDetailResponse() {
    }

    public PdiDetailResponse(List<PdiDetailDto> content, long totalElements, 
                             int totalPages, int currentPage, int size) {
        this.content = content;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.size = size;
    }

    // Getters and Setters
    public List<PdiDetailDto> getContent() {
        return content;
    }

    public void setContent(List<PdiDetailDto> content) {
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

