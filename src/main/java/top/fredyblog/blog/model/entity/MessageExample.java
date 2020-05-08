package top.fredyblog.blog.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MessageExample() {
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

        public Criteria andMessageIdIsNull() {
            addCriterion("message_id is null");
            return (Criteria) this;
        }

        public Criteria andMessageIdIsNotNull() {
            addCriterion("message_id is not null");
            return (Criteria) this;
        }

        public Criteria andMessageIdEqualTo(Integer value) {
            addCriterion("message_id =", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdNotEqualTo(Integer value) {
            addCriterion("message_id <>", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdGreaterThan(Integer value) {
            addCriterion("message_id >", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("message_id >=", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdLessThan(Integer value) {
            addCriterion("message_id <", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdLessThanOrEqualTo(Integer value) {
            addCriterion("message_id <=", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdIn(List<Integer> values) {
            addCriterion("message_id in", values, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdNotIn(List<Integer> values) {
            addCriterion("message_id not in", values, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdBetween(Integer value1, Integer value2) {
            addCriterion("message_id between", value1, value2, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdNotBetween(Integer value1, Integer value2) {
            addCriterion("message_id not between", value1, value2, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdIsNull() {
            addCriterion("message_user_id is null");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdIsNotNull() {
            addCriterion("message_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdEqualTo(Integer value) {
            addCriterion("message_user_id =", value, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdNotEqualTo(Integer value) {
            addCriterion("message_user_id <>", value, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdGreaterThan(Integer value) {
            addCriterion("message_user_id >", value, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("message_user_id >=", value, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdLessThan(Integer value) {
            addCriterion("message_user_id <", value, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("message_user_id <=", value, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdIn(List<Integer> values) {
            addCriterion("message_user_id in", values, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdNotIn(List<Integer> values) {
            addCriterion("message_user_id not in", values, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdBetween(Integer value1, Integer value2) {
            addCriterion("message_user_id between", value1, value2, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("message_user_id not between", value1, value2, "messageUserId");
            return (Criteria) this;
        }

        public Criteria andMessageNicknameIsNull() {
            addCriterion("message_nickname is null");
            return (Criteria) this;
        }

        public Criteria andMessageNicknameIsNotNull() {
            addCriterion("message_nickname is not null");
            return (Criteria) this;
        }

        public Criteria andMessageNicknameEqualTo(String value) {
            addCriterion("message_nickname =", value, "messageNickname");
            return (Criteria) this;
        }

        public Criteria andMessageNicknameNotEqualTo(String value) {
            addCriterion("message_nickname <>", value, "messageNickname");
            return (Criteria) this;
        }

        public Criteria andMessageNicknameGreaterThan(String value) {
            addCriterion("message_nickname >", value, "messageNickname");
            return (Criteria) this;
        }

        public Criteria andMessageNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("message_nickname >=", value, "messageNickname");
            return (Criteria) this;
        }

        public Criteria andMessageNicknameLessThan(String value) {
            addCriterion("message_nickname <", value, "messageNickname");
            return (Criteria) this;
        }

        public Criteria andMessageNicknameLessThanOrEqualTo(String value) {
            addCriterion("message_nickname <=", value, "messageNickname");
            return (Criteria) this;
        }

        public Criteria andMessageNicknameLike(String value) {
            addCriterion("message_nickname like", value, "messageNickname");
            return (Criteria) this;
        }

        public Criteria andMessageNicknameNotLike(String value) {
            addCriterion("message_nickname not like", value, "messageNickname");
            return (Criteria) this;
        }

        public Criteria andMessageNicknameIn(List<String> values) {
            addCriterion("message_nickname in", values, "messageNickname");
            return (Criteria) this;
        }

        public Criteria andMessageNicknameNotIn(List<String> values) {
            addCriterion("message_nickname not in", values, "messageNickname");
            return (Criteria) this;
        }

        public Criteria andMessageNicknameBetween(String value1, String value2) {
            addCriterion("message_nickname between", value1, value2, "messageNickname");
            return (Criteria) this;
        }

        public Criteria andMessageNicknameNotBetween(String value1, String value2) {
            addCriterion("message_nickname not between", value1, value2, "messageNickname");
            return (Criteria) this;
        }

        public Criteria andRepliedUserIdIsNull() {
            addCriterion("replied_user_id is null");
            return (Criteria) this;
        }

        public Criteria andRepliedUserIdIsNotNull() {
            addCriterion("replied_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andRepliedUserIdEqualTo(Integer value) {
            addCriterion("replied_user_id =", value, "repliedUserId");
            return (Criteria) this;
        }

        public Criteria andRepliedUserIdNotEqualTo(Integer value) {
            addCriterion("replied_user_id <>", value, "repliedUserId");
            return (Criteria) this;
        }

        public Criteria andRepliedUserIdGreaterThan(Integer value) {
            addCriterion("replied_user_id >", value, "repliedUserId");
            return (Criteria) this;
        }

        public Criteria andRepliedUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("replied_user_id >=", value, "repliedUserId");
            return (Criteria) this;
        }

        public Criteria andRepliedUserIdLessThan(Integer value) {
            addCriterion("replied_user_id <", value, "repliedUserId");
            return (Criteria) this;
        }

        public Criteria andRepliedUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("replied_user_id <=", value, "repliedUserId");
            return (Criteria) this;
        }

        public Criteria andRepliedUserIdIn(List<Integer> values) {
            addCriterion("replied_user_id in", values, "repliedUserId");
            return (Criteria) this;
        }

        public Criteria andRepliedUserIdNotIn(List<Integer> values) {
            addCriterion("replied_user_id not in", values, "repliedUserId");
            return (Criteria) this;
        }

        public Criteria andRepliedUserIdBetween(Integer value1, Integer value2) {
            addCriterion("replied_user_id between", value1, value2, "repliedUserId");
            return (Criteria) this;
        }

        public Criteria andRepliedUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("replied_user_id not between", value1, value2, "repliedUserId");
            return (Criteria) this;
        }

        public Criteria andRepliedUserNicknameIsNull() {
            addCriterion("replied_user_nickname is null");
            return (Criteria) this;
        }

        public Criteria andRepliedUserNicknameIsNotNull() {
            addCriterion("replied_user_nickname is not null");
            return (Criteria) this;
        }

        public Criteria andRepliedUserNicknameEqualTo(String value) {
            addCriterion("replied_user_nickname =", value, "repliedUserNickname");
            return (Criteria) this;
        }

        public Criteria andRepliedUserNicknameNotEqualTo(String value) {
            addCriterion("replied_user_nickname <>", value, "repliedUserNickname");
            return (Criteria) this;
        }

        public Criteria andRepliedUserNicknameGreaterThan(String value) {
            addCriterion("replied_user_nickname >", value, "repliedUserNickname");
            return (Criteria) this;
        }

        public Criteria andRepliedUserNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("replied_user_nickname >=", value, "repliedUserNickname");
            return (Criteria) this;
        }

        public Criteria andRepliedUserNicknameLessThan(String value) {
            addCriterion("replied_user_nickname <", value, "repliedUserNickname");
            return (Criteria) this;
        }

        public Criteria andRepliedUserNicknameLessThanOrEqualTo(String value) {
            addCriterion("replied_user_nickname <=", value, "repliedUserNickname");
            return (Criteria) this;
        }

        public Criteria andRepliedUserNicknameLike(String value) {
            addCriterion("replied_user_nickname like", value, "repliedUserNickname");
            return (Criteria) this;
        }

        public Criteria andRepliedUserNicknameNotLike(String value) {
            addCriterion("replied_user_nickname not like", value, "repliedUserNickname");
            return (Criteria) this;
        }

        public Criteria andRepliedUserNicknameIn(List<String> values) {
            addCriterion("replied_user_nickname in", values, "repliedUserNickname");
            return (Criteria) this;
        }

        public Criteria andRepliedUserNicknameNotIn(List<String> values) {
            addCriterion("replied_user_nickname not in", values, "repliedUserNickname");
            return (Criteria) this;
        }

        public Criteria andRepliedUserNicknameBetween(String value1, String value2) {
            addCriterion("replied_user_nickname between", value1, value2, "repliedUserNickname");
            return (Criteria) this;
        }

        public Criteria andRepliedUserNicknameNotBetween(String value1, String value2) {
            addCriterion("replied_user_nickname not between", value1, value2, "repliedUserNickname");
            return (Criteria) this;
        }

        public Criteria andMessageEmailIsNull() {
            addCriterion("message_email is null");
            return (Criteria) this;
        }

        public Criteria andMessageEmailIsNotNull() {
            addCriterion("message_email is not null");
            return (Criteria) this;
        }

        public Criteria andMessageEmailEqualTo(String value) {
            addCriterion("message_email =", value, "messageEmail");
            return (Criteria) this;
        }

        public Criteria andMessageEmailNotEqualTo(String value) {
            addCriterion("message_email <>", value, "messageEmail");
            return (Criteria) this;
        }

        public Criteria andMessageEmailGreaterThan(String value) {
            addCriterion("message_email >", value, "messageEmail");
            return (Criteria) this;
        }

        public Criteria andMessageEmailGreaterThanOrEqualTo(String value) {
            addCriterion("message_email >=", value, "messageEmail");
            return (Criteria) this;
        }

        public Criteria andMessageEmailLessThan(String value) {
            addCriterion("message_email <", value, "messageEmail");
            return (Criteria) this;
        }

        public Criteria andMessageEmailLessThanOrEqualTo(String value) {
            addCriterion("message_email <=", value, "messageEmail");
            return (Criteria) this;
        }

        public Criteria andMessageEmailLike(String value) {
            addCriterion("message_email like", value, "messageEmail");
            return (Criteria) this;
        }

        public Criteria andMessageEmailNotLike(String value) {
            addCriterion("message_email not like", value, "messageEmail");
            return (Criteria) this;
        }

        public Criteria andMessageEmailIn(List<String> values) {
            addCriterion("message_email in", values, "messageEmail");
            return (Criteria) this;
        }

        public Criteria andMessageEmailNotIn(List<String> values) {
            addCriterion("message_email not in", values, "messageEmail");
            return (Criteria) this;
        }

        public Criteria andMessageEmailBetween(String value1, String value2) {
            addCriterion("message_email between", value1, value2, "messageEmail");
            return (Criteria) this;
        }

        public Criteria andMessageEmailNotBetween(String value1, String value2) {
            addCriterion("message_email not between", value1, value2, "messageEmail");
            return (Criteria) this;
        }

        public Criteria andMessageLevelIsNull() {
            addCriterion("message_level is null");
            return (Criteria) this;
        }

        public Criteria andMessageLevelIsNotNull() {
            addCriterion("message_level is not null");
            return (Criteria) this;
        }

        public Criteria andMessageLevelEqualTo(String value) {
            addCriterion("message_level =", value, "messageLevel");
            return (Criteria) this;
        }

        public Criteria andMessageLevelNotEqualTo(String value) {
            addCriterion("message_level <>", value, "messageLevel");
            return (Criteria) this;
        }

        public Criteria andMessageLevelGreaterThan(String value) {
            addCriterion("message_level >", value, "messageLevel");
            return (Criteria) this;
        }

        public Criteria andMessageLevelGreaterThanOrEqualTo(String value) {
            addCriterion("message_level >=", value, "messageLevel");
            return (Criteria) this;
        }

        public Criteria andMessageLevelLessThan(String value) {
            addCriterion("message_level <", value, "messageLevel");
            return (Criteria) this;
        }

        public Criteria andMessageLevelLessThanOrEqualTo(String value) {
            addCriterion("message_level <=", value, "messageLevel");
            return (Criteria) this;
        }

        public Criteria andMessageLevelLike(String value) {
            addCriterion("message_level like", value, "messageLevel");
            return (Criteria) this;
        }

        public Criteria andMessageLevelNotLike(String value) {
            addCriterion("message_level not like", value, "messageLevel");
            return (Criteria) this;
        }

        public Criteria andMessageLevelIn(List<String> values) {
            addCriterion("message_level in", values, "messageLevel");
            return (Criteria) this;
        }

        public Criteria andMessageLevelNotIn(List<String> values) {
            addCriterion("message_level not in", values, "messageLevel");
            return (Criteria) this;
        }

        public Criteria andMessageLevelBetween(String value1, String value2) {
            addCriterion("message_level between", value1, value2, "messageLevel");
            return (Criteria) this;
        }

        public Criteria andMessageLevelNotBetween(String value1, String value2) {
            addCriterion("message_level not between", value1, value2, "messageLevel");
            return (Criteria) this;
        }

        public Criteria andParentMessageIdIsNull() {
            addCriterion("parent_message_id is null");
            return (Criteria) this;
        }

        public Criteria andParentMessageIdIsNotNull() {
            addCriterion("parent_message_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentMessageIdEqualTo(Integer value) {
            addCriterion("parent_message_id =", value, "parentMessageId");
            return (Criteria) this;
        }

        public Criteria andParentMessageIdNotEqualTo(Integer value) {
            addCriterion("parent_message_id <>", value, "parentMessageId");
            return (Criteria) this;
        }

        public Criteria andParentMessageIdGreaterThan(Integer value) {
            addCriterion("parent_message_id >", value, "parentMessageId");
            return (Criteria) this;
        }

        public Criteria andParentMessageIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("parent_message_id >=", value, "parentMessageId");
            return (Criteria) this;
        }

        public Criteria andParentMessageIdLessThan(Integer value) {
            addCriterion("parent_message_id <", value, "parentMessageId");
            return (Criteria) this;
        }

        public Criteria andParentMessageIdLessThanOrEqualTo(Integer value) {
            addCriterion("parent_message_id <=", value, "parentMessageId");
            return (Criteria) this;
        }

        public Criteria andParentMessageIdIn(List<Integer> values) {
            addCriterion("parent_message_id in", values, "parentMessageId");
            return (Criteria) this;
        }

        public Criteria andParentMessageIdNotIn(List<Integer> values) {
            addCriterion("parent_message_id not in", values, "parentMessageId");
            return (Criteria) this;
        }

        public Criteria andParentMessageIdBetween(Integer value1, Integer value2) {
            addCriterion("parent_message_id between", value1, value2, "parentMessageId");
            return (Criteria) this;
        }

        public Criteria andParentMessageIdNotBetween(Integer value1, Integer value2) {
            addCriterion("parent_message_id not between", value1, value2, "parentMessageId");
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