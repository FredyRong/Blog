package top.fredyblog.blog.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AnnouncementExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> createTimeCriteria;

        protected List<Criterion> updateTimeCriteria;

        protected List<Criterion> delTimeCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
            createTimeCriteria = new ArrayList<>();
            updateTimeCriteria = new ArrayList<>();
            delTimeCriteria = new ArrayList<>();
        }

        public List<Criterion> getCreateTimeCriteria() {
            return createTimeCriteria;
        }

        protected void addCreateTimeCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            createTimeCriteria.add(new Criterion(condition, value, "org.apache.ibatis.type.LocalDateTimeTypeHandler"));
            allCriteria = null;
        }

        protected void addCreateTimeCriterion(String condition, LocalDateTime value1, LocalDateTime value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            createTimeCriteria.add(new Criterion(condition, value1, value2, "org.apache.ibatis.type.LocalDateTimeTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getUpdateTimeCriteria() {
            return updateTimeCriteria;
        }

        protected void addUpdateTimeCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            updateTimeCriteria.add(new Criterion(condition, value, "org.apache.ibatis.type.LocalDateTimeTypeHandler"));
            allCriteria = null;
        }

        protected void addUpdateTimeCriterion(String condition, LocalDateTime value1, LocalDateTime value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            updateTimeCriteria.add(new Criterion(condition, value1, value2, "org.apache.ibatis.type.LocalDateTimeTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getDelTimeCriteria() {
            return delTimeCriteria;
        }

        protected void addDelTimeCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            delTimeCriteria.add(new Criterion(condition, value, "org.apache.ibatis.type.LocalDateTimeTypeHandler"));
            allCriteria = null;
        }

        protected void addDelTimeCriterion(String condition, LocalDateTime value1, LocalDateTime value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            delTimeCriteria.add(new Criterion(condition, value1, value2, "org.apache.ibatis.type.LocalDateTimeTypeHandler"));
            allCriteria = null;
        }

        public boolean isValid() {
            return criteria.size() > 0
                || createTimeCriteria.size() > 0
                || updateTimeCriteria.size() > 0
                || delTimeCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(createTimeCriteria);
                allCriteria.addAll(updateTimeCriteria);
                allCriteria.addAll(delTimeCriteria);
            }
            return allCriteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
            allCriteria = null;
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
            allCriteria = null;
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
            allCriteria = null;
        }

        public Criteria andAnncIdIsNull() {
            addCriterion("annc_id is null");
            return (Criteria) this;
        }

        public Criteria andAnncIdIsNotNull() {
            addCriterion("annc_id is not null");
            return (Criteria) this;
        }

        public Criteria andAnncIdEqualTo(Integer value) {
            addCriterion("annc_id =", value, "anncId");
            return (Criteria) this;
        }

        public Criteria andAnncIdNotEqualTo(Integer value) {
            addCriterion("annc_id <>", value, "anncId");
            return (Criteria) this;
        }

        public Criteria andAnncIdGreaterThan(Integer value) {
            addCriterion("annc_id >", value, "anncId");
            return (Criteria) this;
        }

        public Criteria andAnncIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("annc_id >=", value, "anncId");
            return (Criteria) this;
        }

        public Criteria andAnncIdLessThan(Integer value) {
            addCriterion("annc_id <", value, "anncId");
            return (Criteria) this;
        }

        public Criteria andAnncIdLessThanOrEqualTo(Integer value) {
            addCriterion("annc_id <=", value, "anncId");
            return (Criteria) this;
        }

        public Criteria andAnncIdIn(List<Integer> values) {
            addCriterion("annc_id in", values, "anncId");
            return (Criteria) this;
        }

        public Criteria andAnncIdNotIn(List<Integer> values) {
            addCriterion("annc_id not in", values, "anncId");
            return (Criteria) this;
        }

        public Criteria andAnncIdBetween(Integer value1, Integer value2) {
            addCriterion("annc_id between", value1, value2, "anncId");
            return (Criteria) this;
        }

        public Criteria andAnncIdNotBetween(Integer value1, Integer value2) {
            addCriterion("annc_id not between", value1, value2, "anncId");
            return (Criteria) this;
        }

        public Criteria andAnncTitleIsNull() {
            addCriterion("annc_title is null");
            return (Criteria) this;
        }

        public Criteria andAnncTitleIsNotNull() {
            addCriterion("annc_title is not null");
            return (Criteria) this;
        }

        public Criteria andAnncTitleEqualTo(String value) {
            addCriterion("annc_title =", value, "anncTitle");
            return (Criteria) this;
        }

        public Criteria andAnncTitleNotEqualTo(String value) {
            addCriterion("annc_title <>", value, "anncTitle");
            return (Criteria) this;
        }

        public Criteria andAnncTitleGreaterThan(String value) {
            addCriterion("annc_title >", value, "anncTitle");
            return (Criteria) this;
        }

        public Criteria andAnncTitleGreaterThanOrEqualTo(String value) {
            addCriterion("annc_title >=", value, "anncTitle");
            return (Criteria) this;
        }

        public Criteria andAnncTitleLessThan(String value) {
            addCriterion("annc_title <", value, "anncTitle");
            return (Criteria) this;
        }

        public Criteria andAnncTitleLessThanOrEqualTo(String value) {
            addCriterion("annc_title <=", value, "anncTitle");
            return (Criteria) this;
        }

        public Criteria andAnncTitleLike(String value) {
            addCriterion("annc_title like", value, "anncTitle");
            return (Criteria) this;
        }

        public Criteria andAnncTitleNotLike(String value) {
            addCriterion("annc_title not like", value, "anncTitle");
            return (Criteria) this;
        }

        public Criteria andAnncTitleIn(List<String> values) {
            addCriterion("annc_title in", values, "anncTitle");
            return (Criteria) this;
        }

        public Criteria andAnncTitleNotIn(List<String> values) {
            addCriterion("annc_title not in", values, "anncTitle");
            return (Criteria) this;
        }

        public Criteria andAnncTitleBetween(String value1, String value2) {
            addCriterion("annc_title between", value1, value2, "anncTitle");
            return (Criteria) this;
        }

        public Criteria andAnncTitleNotBetween(String value1, String value2) {
            addCriterion("annc_title not between", value1, value2, "anncTitle");
            return (Criteria) this;
        }

        public Criteria andAnncDescIsNull() {
            addCriterion("annc_desc is null");
            return (Criteria) this;
        }

        public Criteria andAnncDescIsNotNull() {
            addCriterion("annc_desc is not null");
            return (Criteria) this;
        }

        public Criteria andAnncDescEqualTo(String value) {
            addCriterion("annc_desc =", value, "anncDesc");
            return (Criteria) this;
        }

        public Criteria andAnncDescNotEqualTo(String value) {
            addCriterion("annc_desc <>", value, "anncDesc");
            return (Criteria) this;
        }

        public Criteria andAnncDescGreaterThan(String value) {
            addCriterion("annc_desc >", value, "anncDesc");
            return (Criteria) this;
        }

        public Criteria andAnncDescGreaterThanOrEqualTo(String value) {
            addCriterion("annc_desc >=", value, "anncDesc");
            return (Criteria) this;
        }

        public Criteria andAnncDescLessThan(String value) {
            addCriterion("annc_desc <", value, "anncDesc");
            return (Criteria) this;
        }

        public Criteria andAnncDescLessThanOrEqualTo(String value) {
            addCriterion("annc_desc <=", value, "anncDesc");
            return (Criteria) this;
        }

        public Criteria andAnncDescLike(String value) {
            addCriterion("annc_desc like", value, "anncDesc");
            return (Criteria) this;
        }

        public Criteria andAnncDescNotLike(String value) {
            addCriterion("annc_desc not like", value, "anncDesc");
            return (Criteria) this;
        }

        public Criteria andAnncDescIn(List<String> values) {
            addCriterion("annc_desc in", values, "anncDesc");
            return (Criteria) this;
        }

        public Criteria andAnncDescNotIn(List<String> values) {
            addCriterion("annc_desc not in", values, "anncDesc");
            return (Criteria) this;
        }

        public Criteria andAnncDescBetween(String value1, String value2) {
            addCriterion("annc_desc between", value1, value2, "anncDesc");
            return (Criteria) this;
        }

        public Criteria andAnncDescNotBetween(String value1, String value2) {
            addCriterion("annc_desc not between", value1, value2, "anncDesc");
            return (Criteria) this;
        }

        public Criteria andTopFlagIsNull() {
            addCriterion("top_flag is null");
            return (Criteria) this;
        }

        public Criteria andTopFlagIsNotNull() {
            addCriterion("top_flag is not null");
            return (Criteria) this;
        }

        public Criteria andTopFlagEqualTo(Boolean value) {
            addCriterion("top_flag =", value, "topFlag");
            return (Criteria) this;
        }

        public Criteria andTopFlagNotEqualTo(Boolean value) {
            addCriterion("top_flag <>", value, "topFlag");
            return (Criteria) this;
        }

        public Criteria andTopFlagGreaterThan(Boolean value) {
            addCriterion("top_flag >", value, "topFlag");
            return (Criteria) this;
        }

        public Criteria andTopFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("top_flag >=", value, "topFlag");
            return (Criteria) this;
        }

        public Criteria andTopFlagLessThan(Boolean value) {
            addCriterion("top_flag <", value, "topFlag");
            return (Criteria) this;
        }

        public Criteria andTopFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("top_flag <=", value, "topFlag");
            return (Criteria) this;
        }

        public Criteria andTopFlagIn(List<Boolean> values) {
            addCriterion("top_flag in", values, "topFlag");
            return (Criteria) this;
        }

        public Criteria andTopFlagNotIn(List<Boolean> values) {
            addCriterion("top_flag not in", values, "topFlag");
            return (Criteria) this;
        }

        public Criteria andTopFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("top_flag between", value1, value2, "topFlag");
            return (Criteria) this;
        }

        public Criteria andTopFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("top_flag not between", value1, value2, "topFlag");
            return (Criteria) this;
        }

        public Criteria andPublishedIsNull() {
            addCriterion("published is null");
            return (Criteria) this;
        }

        public Criteria andPublishedIsNotNull() {
            addCriterion("published is not null");
            return (Criteria) this;
        }

        public Criteria andPublishedEqualTo(Boolean value) {
            addCriterion("published =", value, "published");
            return (Criteria) this;
        }

        public Criteria andPublishedNotEqualTo(Boolean value) {
            addCriterion("published <>", value, "published");
            return (Criteria) this;
        }

        public Criteria andPublishedGreaterThan(Boolean value) {
            addCriterion("published >", value, "published");
            return (Criteria) this;
        }

        public Criteria andPublishedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("published >=", value, "published");
            return (Criteria) this;
        }

        public Criteria andPublishedLessThan(Boolean value) {
            addCriterion("published <", value, "published");
            return (Criteria) this;
        }

        public Criteria andPublishedLessThanOrEqualTo(Boolean value) {
            addCriterion("published <=", value, "published");
            return (Criteria) this;
        }

        public Criteria andPublishedIn(List<Boolean> values) {
            addCriterion("published in", values, "published");
            return (Criteria) this;
        }

        public Criteria andPublishedNotIn(List<Boolean> values) {
            addCriterion("published not in", values, "published");
            return (Criteria) this;
        }

        public Criteria andPublishedBetween(Boolean value1, Boolean value2) {
            addCriterion("published between", value1, value2, "published");
            return (Criteria) this;
        }

        public Criteria andPublishedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("published not between", value1, value2, "published");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(LocalDateTime value) {
            addCreateTimeCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(LocalDateTime value) {
            addCreateTimeCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(LocalDateTime value) {
            addCreateTimeCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCreateTimeCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(LocalDateTime value) {
            addCreateTimeCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(LocalDateTime value) {
            addCreateTimeCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<LocalDateTime> values) {
            addCreateTimeCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<LocalDateTime> values) {
            addCreateTimeCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCreateTimeCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCreateTimeCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(LocalDateTime value) {
            addUpdateTimeCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(LocalDateTime value) {
            addUpdateTimeCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(LocalDateTime value) {
            addUpdateTimeCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addUpdateTimeCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(LocalDateTime value) {
            addUpdateTimeCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(LocalDateTime value) {
            addUpdateTimeCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<LocalDateTime> values) {
            addUpdateTimeCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<LocalDateTime> values) {
            addUpdateTimeCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addUpdateTimeCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addUpdateTimeCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNull() {
            addCriterion("del_flag is null");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNotNull() {
            addCriterion("del_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlagEqualTo(Boolean value) {
            addCriterion("del_flag =", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotEqualTo(Boolean value) {
            addCriterion("del_flag <>", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThan(Boolean value) {
            addCriterion("del_flag >", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("del_flag >=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThan(Boolean value) {
            addCriterion("del_flag <", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("del_flag <=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagIn(List<Boolean> values) {
            addCriterion("del_flag in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotIn(List<Boolean> values) {
            addCriterion("del_flag not in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("del_flag between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("del_flag not between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelTimeIsNull() {
            addCriterion("del_time is null");
            return (Criteria) this;
        }

        public Criteria andDelTimeIsNotNull() {
            addCriterion("del_time is not null");
            return (Criteria) this;
        }

        public Criteria andDelTimeEqualTo(LocalDateTime value) {
            addDelTimeCriterion("del_time =", value, "delTime");
            return (Criteria) this;
        }

        public Criteria andDelTimeNotEqualTo(LocalDateTime value) {
            addDelTimeCriterion("del_time <>", value, "delTime");
            return (Criteria) this;
        }

        public Criteria andDelTimeGreaterThan(LocalDateTime value) {
            addDelTimeCriterion("del_time >", value, "delTime");
            return (Criteria) this;
        }

        public Criteria andDelTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addDelTimeCriterion("del_time >=", value, "delTime");
            return (Criteria) this;
        }

        public Criteria andDelTimeLessThan(LocalDateTime value) {
            addDelTimeCriterion("del_time <", value, "delTime");
            return (Criteria) this;
        }

        public Criteria andDelTimeLessThanOrEqualTo(LocalDateTime value) {
            addDelTimeCriterion("del_time <=", value, "delTime");
            return (Criteria) this;
        }

        public Criteria andDelTimeIn(List<LocalDateTime> values) {
            addDelTimeCriterion("del_time in", values, "delTime");
            return (Criteria) this;
        }

        public Criteria andDelTimeNotIn(List<LocalDateTime> values) {
            addDelTimeCriterion("del_time not in", values, "delTime");
            return (Criteria) this;
        }

        public Criteria andDelTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addDelTimeCriterion("del_time between", value1, value2, "delTime");
            return (Criteria) this;
        }

        public Criteria andDelTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addDelTimeCriterion("del_time not between", value1, value2, "delTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}