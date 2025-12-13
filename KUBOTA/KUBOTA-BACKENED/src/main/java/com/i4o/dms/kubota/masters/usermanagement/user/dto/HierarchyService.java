package com.i4o.dms.kubota.masters.usermanagement.user.dto;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class HierarchyService<T extends IElement<R>, R> {

    private final Collection<T> all;

    public HierarchyService(Collection<T> all) {
        this.all = all;
    }

    public Collection<T> getRoots() {
        return this.all.stream()
                .filter(this::isRoot)
                .collect(Collectors.toSet());
    }

    public TreeNode<T> getTree(T element) {
        final Collection<T> children = this.getChildren(element);
        return children.isEmpty() ? new TreeNode<>(element) : new TreeNode<>(element, children.stream()
                .map(this::getTree)
                .collect(Collectors.toSet()));
    }

    public Collection<T> getChildren(T parent) {
        return this.all.stream()
                .filter(element -> parent.checkId(element.getParentId()))
                .collect(Collectors.toSet());
    }
    
    public boolean isRoot(T element) {
        return !Optional.ofNullable(element.getParentId()).isPresent();
    }
}