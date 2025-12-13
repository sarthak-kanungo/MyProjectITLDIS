package com.i4o.dms.kubota.feedback.repository;

import com.i4o.dms.kubota.feedback.domain.FeedbackForm;

import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface FeedbackFormRepository extends JpaRepository<FeedbackForm,Long> {
    @Query(value = "{call sp_feedback_searchFeedback(:dealerId,:page,:size)}", nativeQuery = true)
    List<Map<Object,String>> searchFeedback(@Param("dealerId")Long dealerId,@Param("page") Integer page,@Param("size") Integer size);

    @Query(value = "{call sp_feedback_searchFeedback_count(:dealerId,:page,:size)}", nativeQuery = true)
    Long searchFeedbackCount(@Param("dealerId")Long dealerId,@Param("page")Integer page,@Param("size") Integer size);


}
