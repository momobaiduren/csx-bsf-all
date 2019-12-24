package com.yh.csx.bsf.demo.cat.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CustomerExample() {
        oredCriteria = new ArrayList<Criteria>();
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
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberIsNull() {
            addCriterion("customer_number is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberIsNotNull() {
            addCriterion("customer_number is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberEqualTo(String value) {
            addCriterion("customer_number =", value, "customerNumber");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberNotEqualTo(String value) {
            addCriterion("customer_number <>", value, "customerNumber");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberGreaterThan(String value) {
            addCriterion("customer_number >", value, "customerNumber");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberGreaterThanOrEqualTo(String value) {
            addCriterion("customer_number >=", value, "customerNumber");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberLessThan(String value) {
            addCriterion("customer_number <", value, "customerNumber");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberLessThanOrEqualTo(String value) {
            addCriterion("customer_number <=", value, "customerNumber");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberLike(String value) {
            addCriterion("customer_number like", value, "customerNumber");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberNotLike(String value) {
            addCriterion("customer_number not like", value, "customerNumber");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberIn(List<String> values) {
            addCriterion("customer_number in", values, "customerNumber");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberNotIn(List<String> values) {
            addCriterion("customer_number not in", values, "customerNumber");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberBetween(String value1, String value2) {
            addCriterion("customer_number between", value1, value2, "customerNumber");
            return (Criteria) this;
        }

        public Criteria andCustomerNumberNotBetween(String value1, String value2) {
            addCriterion("customer_number not between", value1, value2, "customerNumber");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNull() {
            addCriterion("customer_name is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNotNull() {
            addCriterion("customer_name is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameEqualTo(String value) {
            addCriterion("customer_name =", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotEqualTo(String value) {
            addCriterion("customer_name <>", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThan(String value) {
            addCriterion("customer_name >", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("customer_name >=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThan(String value) {
            addCriterion("customer_name <", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("customer_name <=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLike(String value) {
            addCriterion("customer_name like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotLike(String value) {
            addCriterion("customer_name not like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIn(List<String> values) {
            addCriterion("customer_name in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotIn(List<String> values) {
            addCriterion("customer_name not in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameBetween(String value1, String value2) {
            addCriterion("customer_name between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotBetween(String value1, String value2) {
            addCriterion("customer_name not between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andFirstCategoryCodeIsNull() {
            addCriterion("first_category_code is null");
            return (Criteria) this;
        }

        public Criteria andFirstCategoryCodeIsNotNull() {
            addCriterion("first_category_code is not null");
            return (Criteria) this;
        }

        public Criteria andFirstCategoryCodeEqualTo(String value) {
            addCriterion("first_category_code =", value, "firstCategoryCode");
            return (Criteria) this;
        }

        public Criteria andFirstCategoryCodeNotEqualTo(String value) {
            addCriterion("first_category_code <>", value, "firstCategoryCode");
            return (Criteria) this;
        }

        public Criteria andFirstCategoryCodeGreaterThan(String value) {
            addCriterion("first_category_code >", value, "firstCategoryCode");
            return (Criteria) this;
        }

        public Criteria andFirstCategoryCodeGreaterThanOrEqualTo(String value) {
            addCriterion("first_category_code >=", value, "firstCategoryCode");
            return (Criteria) this;
        }

        public Criteria andFirstCategoryCodeLessThan(String value) {
            addCriterion("first_category_code <", value, "firstCategoryCode");
            return (Criteria) this;
        }

        public Criteria andFirstCategoryCodeLessThanOrEqualTo(String value) {
            addCriterion("first_category_code <=", value, "firstCategoryCode");
            return (Criteria) this;
        }

        public Criteria andFirstCategoryCodeLike(String value) {
            addCriterion("first_category_code like", value, "firstCategoryCode");
            return (Criteria) this;
        }

        public Criteria andFirstCategoryCodeNotLike(String value) {
            addCriterion("first_category_code not like", value, "firstCategoryCode");
            return (Criteria) this;
        }

        public Criteria andFirstCategoryCodeIn(List<String> values) {
            addCriterion("first_category_code in", values, "firstCategoryCode");
            return (Criteria) this;
        }

        public Criteria andFirstCategoryCodeNotIn(List<String> values) {
            addCriterion("first_category_code not in", values, "firstCategoryCode");
            return (Criteria) this;
        }

        public Criteria andFirstCategoryCodeBetween(String value1, String value2) {
            addCriterion("first_category_code between", value1, value2, "firstCategoryCode");
            return (Criteria) this;
        }

        public Criteria andFirstCategoryCodeNotBetween(String value1, String value2) {
            addCriterion("first_category_code not between", value1, value2, "firstCategoryCode");
            return (Criteria) this;
        }

        public Criteria andSecondCategoryCodeIsNull() {
            addCriterion("second_category_code is null");
            return (Criteria) this;
        }

        public Criteria andSecondCategoryCodeIsNotNull() {
            addCriterion("second_category_code is not null");
            return (Criteria) this;
        }

        public Criteria andSecondCategoryCodeEqualTo(String value) {
            addCriterion("second_category_code =", value, "secondCategoryCode");
            return (Criteria) this;
        }

        public Criteria andSecondCategoryCodeNotEqualTo(String value) {
            addCriterion("second_category_code <>", value, "secondCategoryCode");
            return (Criteria) this;
        }

        public Criteria andSecondCategoryCodeGreaterThan(String value) {
            addCriterion("second_category_code >", value, "secondCategoryCode");
            return (Criteria) this;
        }

        public Criteria andSecondCategoryCodeGreaterThanOrEqualTo(String value) {
            addCriterion("second_category_code >=", value, "secondCategoryCode");
            return (Criteria) this;
        }

        public Criteria andSecondCategoryCodeLessThan(String value) {
            addCriterion("second_category_code <", value, "secondCategoryCode");
            return (Criteria) this;
        }

        public Criteria andSecondCategoryCodeLessThanOrEqualTo(String value) {
            addCriterion("second_category_code <=", value, "secondCategoryCode");
            return (Criteria) this;
        }

        public Criteria andSecondCategoryCodeLike(String value) {
            addCriterion("second_category_code like", value, "secondCategoryCode");
            return (Criteria) this;
        }

        public Criteria andSecondCategoryCodeNotLike(String value) {
            addCriterion("second_category_code not like", value, "secondCategoryCode");
            return (Criteria) this;
        }

        public Criteria andSecondCategoryCodeIn(List<String> values) {
            addCriterion("second_category_code in", values, "secondCategoryCode");
            return (Criteria) this;
        }

        public Criteria andSecondCategoryCodeNotIn(List<String> values) {
            addCriterion("second_category_code not in", values, "secondCategoryCode");
            return (Criteria) this;
        }

        public Criteria andSecondCategoryCodeBetween(String value1, String value2) {
            addCriterion("second_category_code between", value1, value2, "secondCategoryCode");
            return (Criteria) this;
        }

        public Criteria andSecondCategoryCodeNotBetween(String value1, String value2) {
            addCriterion("second_category_code not between", value1, value2, "secondCategoryCode");
            return (Criteria) this;
        }

        public Criteria andThirdCategoryCodeIsNull() {
            addCriterion("third_category_code is null");
            return (Criteria) this;
        }

        public Criteria andThirdCategoryCodeIsNotNull() {
            addCriterion("third_category_code is not null");
            return (Criteria) this;
        }

        public Criteria andThirdCategoryCodeEqualTo(String value) {
            addCriterion("third_category_code =", value, "thirdCategoryCode");
            return (Criteria) this;
        }

        public Criteria andThirdCategoryCodeNotEqualTo(String value) {
            addCriterion("third_category_code <>", value, "thirdCategoryCode");
            return (Criteria) this;
        }

        public Criteria andThirdCategoryCodeGreaterThan(String value) {
            addCriterion("third_category_code >", value, "thirdCategoryCode");
            return (Criteria) this;
        }

        public Criteria andThirdCategoryCodeGreaterThanOrEqualTo(String value) {
            addCriterion("third_category_code >=", value, "thirdCategoryCode");
            return (Criteria) this;
        }

        public Criteria andThirdCategoryCodeLessThan(String value) {
            addCriterion("third_category_code <", value, "thirdCategoryCode");
            return (Criteria) this;
        }

        public Criteria andThirdCategoryCodeLessThanOrEqualTo(String value) {
            addCriterion("third_category_code <=", value, "thirdCategoryCode");
            return (Criteria) this;
        }

        public Criteria andThirdCategoryCodeLike(String value) {
            addCriterion("third_category_code like", value, "thirdCategoryCode");
            return (Criteria) this;
        }

        public Criteria andThirdCategoryCodeNotLike(String value) {
            addCriterion("third_category_code not like", value, "thirdCategoryCode");
            return (Criteria) this;
        }

        public Criteria andThirdCategoryCodeIn(List<String> values) {
            addCriterion("third_category_code in", values, "thirdCategoryCode");
            return (Criteria) this;
        }

        public Criteria andThirdCategoryCodeNotIn(List<String> values) {
            addCriterion("third_category_code not in", values, "thirdCategoryCode");
            return (Criteria) this;
        }

        public Criteria andThirdCategoryCodeBetween(String value1, String value2) {
            addCriterion("third_category_code between", value1, value2, "thirdCategoryCode");
            return (Criteria) this;
        }

        public Criteria andThirdCategoryCodeNotBetween(String value1, String value2) {
            addCriterion("third_category_code not between", value1, value2, "thirdCategoryCode");
            return (Criteria) this;
        }

        public Criteria andCustomerCategoryIdIsNull() {
            addCriterion("customer_category_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomerCategoryIdIsNotNull() {
            addCriterion("customer_category_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerCategoryIdEqualTo(Integer value) {
            addCriterion("customer_category_id =", value, "customerCategoryId");
            return (Criteria) this;
        }

        public Criteria andCustomerCategoryIdNotEqualTo(Integer value) {
            addCriterion("customer_category_id <>", value, "customerCategoryId");
            return (Criteria) this;
        }

        public Criteria andCustomerCategoryIdGreaterThan(Integer value) {
            addCriterion("customer_category_id >", value, "customerCategoryId");
            return (Criteria) this;
        }

        public Criteria andCustomerCategoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("customer_category_id >=", value, "customerCategoryId");
            return (Criteria) this;
        }

        public Criteria andCustomerCategoryIdLessThan(Integer value) {
            addCriterion("customer_category_id <", value, "customerCategoryId");
            return (Criteria) this;
        }

        public Criteria andCustomerCategoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("customer_category_id <=", value, "customerCategoryId");
            return (Criteria) this;
        }

        public Criteria andCustomerCategoryIdIn(List<Integer> values) {
            addCriterion("customer_category_id in", values, "customerCategoryId");
            return (Criteria) this;
        }

        public Criteria andCustomerCategoryIdNotIn(List<Integer> values) {
            addCriterion("customer_category_id not in", values, "customerCategoryId");
            return (Criteria) this;
        }

        public Criteria andCustomerCategoryIdBetween(Integer value1, Integer value2) {
            addCriterion("customer_category_id between", value1, value2, "customerCategoryId");
            return (Criteria) this;
        }

        public Criteria andCustomerCategoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("customer_category_id not between", value1, value2, "customerCategoryId");
            return (Criteria) this;
        }

        public Criteria andArchiveCategoryIsNull() {
            addCriterion("archive_category is null");
            return (Criteria) this;
        }

        public Criteria andArchiveCategoryIsNotNull() {
            addCriterion("archive_category is not null");
            return (Criteria) this;
        }

        public Criteria andArchiveCategoryEqualTo(String value) {
            addCriterion("archive_category =", value, "archiveCategory");
            return (Criteria) this;
        }

        public Criteria andArchiveCategoryNotEqualTo(String value) {
            addCriterion("archive_category <>", value, "archiveCategory");
            return (Criteria) this;
        }

        public Criteria andArchiveCategoryGreaterThan(String value) {
            addCriterion("archive_category >", value, "archiveCategory");
            return (Criteria) this;
        }

        public Criteria andArchiveCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("archive_category >=", value, "archiveCategory");
            return (Criteria) this;
        }

        public Criteria andArchiveCategoryLessThan(String value) {
            addCriterion("archive_category <", value, "archiveCategory");
            return (Criteria) this;
        }

        public Criteria andArchiveCategoryLessThanOrEqualTo(String value) {
            addCriterion("archive_category <=", value, "archiveCategory");
            return (Criteria) this;
        }

        public Criteria andArchiveCategoryLike(String value) {
            addCriterion("archive_category like", value, "archiveCategory");
            return (Criteria) this;
        }

        public Criteria andArchiveCategoryNotLike(String value) {
            addCriterion("archive_category not like", value, "archiveCategory");
            return (Criteria) this;
        }

        public Criteria andArchiveCategoryIn(List<String> values) {
            addCriterion("archive_category in", values, "archiveCategory");
            return (Criteria) this;
        }

        public Criteria andArchiveCategoryNotIn(List<String> values) {
            addCriterion("archive_category not in", values, "archiveCategory");
            return (Criteria) this;
        }

        public Criteria andArchiveCategoryBetween(String value1, String value2) {
            addCriterion("archive_category between", value1, value2, "archiveCategory");
            return (Criteria) this;
        }

        public Criteria andArchiveCategoryNotBetween(String value1, String value2) {
            addCriterion("archive_category not between", value1, value2, "archiveCategory");
            return (Criteria) this;
        }

        public Criteria andCooperationModeIsNull() {
            addCriterion("cooperation_mode is null");
            return (Criteria) this;
        }

        public Criteria andCooperationModeIsNotNull() {
            addCriterion("cooperation_mode is not null");
            return (Criteria) this;
        }

        public Criteria andCooperationModeEqualTo(String value) {
            addCriterion("cooperation_mode =", value, "cooperationMode");
            return (Criteria) this;
        }

        public Criteria andCooperationModeNotEqualTo(String value) {
            addCriterion("cooperation_mode <>", value, "cooperationMode");
            return (Criteria) this;
        }

        public Criteria andCooperationModeGreaterThan(String value) {
            addCriterion("cooperation_mode >", value, "cooperationMode");
            return (Criteria) this;
        }

        public Criteria andCooperationModeGreaterThanOrEqualTo(String value) {
            addCriterion("cooperation_mode >=", value, "cooperationMode");
            return (Criteria) this;
        }

        public Criteria andCooperationModeLessThan(String value) {
            addCriterion("cooperation_mode <", value, "cooperationMode");
            return (Criteria) this;
        }

        public Criteria andCooperationModeLessThanOrEqualTo(String value) {
            addCriterion("cooperation_mode <=", value, "cooperationMode");
            return (Criteria) this;
        }

        public Criteria andCooperationModeLike(String value) {
            addCriterion("cooperation_mode like", value, "cooperationMode");
            return (Criteria) this;
        }

        public Criteria andCooperationModeNotLike(String value) {
            addCriterion("cooperation_mode not like", value, "cooperationMode");
            return (Criteria) this;
        }

        public Criteria andCooperationModeIn(List<String> values) {
            addCriterion("cooperation_mode in", values, "cooperationMode");
            return (Criteria) this;
        }

        public Criteria andCooperationModeNotIn(List<String> values) {
            addCriterion("cooperation_mode not in", values, "cooperationMode");
            return (Criteria) this;
        }

        public Criteria andCooperationModeBetween(String value1, String value2) {
            addCriterion("cooperation_mode between", value1, value2, "cooperationMode");
            return (Criteria) this;
        }

        public Criteria andCooperationModeNotBetween(String value1, String value2) {
            addCriterion("cooperation_mode not between", value1, value2, "cooperationMode");
            return (Criteria) this;
        }

        public Criteria andSocialCreditCodeIsNull() {
            addCriterion("social_credit_code is null");
            return (Criteria) this;
        }

        public Criteria andSocialCreditCodeIsNotNull() {
            addCriterion("social_credit_code is not null");
            return (Criteria) this;
        }

        public Criteria andSocialCreditCodeEqualTo(String value) {
            addCriterion("social_credit_code =", value, "socialCreditCode");
            return (Criteria) this;
        }

        public Criteria andSocialCreditCodeNotEqualTo(String value) {
            addCriterion("social_credit_code <>", value, "socialCreditCode");
            return (Criteria) this;
        }

        public Criteria andSocialCreditCodeGreaterThan(String value) {
            addCriterion("social_credit_code >", value, "socialCreditCode");
            return (Criteria) this;
        }

        public Criteria andSocialCreditCodeGreaterThanOrEqualTo(String value) {
            addCriterion("social_credit_code >=", value, "socialCreditCode");
            return (Criteria) this;
        }

        public Criteria andSocialCreditCodeLessThan(String value) {
            addCriterion("social_credit_code <", value, "socialCreditCode");
            return (Criteria) this;
        }

        public Criteria andSocialCreditCodeLessThanOrEqualTo(String value) {
            addCriterion("social_credit_code <=", value, "socialCreditCode");
            return (Criteria) this;
        }

        public Criteria andSocialCreditCodeLike(String value) {
            addCriterion("social_credit_code like", value, "socialCreditCode");
            return (Criteria) this;
        }

        public Criteria andSocialCreditCodeNotLike(String value) {
            addCriterion("social_credit_code not like", value, "socialCreditCode");
            return (Criteria) this;
        }

        public Criteria andSocialCreditCodeIn(List<String> values) {
            addCriterion("social_credit_code in", values, "socialCreditCode");
            return (Criteria) this;
        }

        public Criteria andSocialCreditCodeNotIn(List<String> values) {
            addCriterion("social_credit_code not in", values, "socialCreditCode");
            return (Criteria) this;
        }

        public Criteria andSocialCreditCodeBetween(String value1, String value2) {
            addCriterion("social_credit_code between", value1, value2, "socialCreditCode");
            return (Criteria) this;
        }

        public Criteria andSocialCreditCodeNotBetween(String value1, String value2) {
            addCriterion("social_credit_code not between", value1, value2, "socialCreditCode");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseIsNull() {
            addCriterion("business_license is null");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseIsNotNull() {
            addCriterion("business_license is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseEqualTo(String value) {
            addCriterion("business_license =", value, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseNotEqualTo(String value) {
            addCriterion("business_license <>", value, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseGreaterThan(String value) {
            addCriterion("business_license >", value, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseGreaterThanOrEqualTo(String value) {
            addCriterion("business_license >=", value, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseLessThan(String value) {
            addCriterion("business_license <", value, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseLessThanOrEqualTo(String value) {
            addCriterion("business_license <=", value, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseLike(String value) {
            addCriterion("business_license like", value, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseNotLike(String value) {
            addCriterion("business_license not like", value, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseIn(List<String> values) {
            addCriterion("business_license in", values, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseNotIn(List<String> values) {
            addCriterion("business_license not in", values, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseBetween(String value1, String value2) {
            addCriterion("business_license between", value1, value2, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseNotBetween(String value1, String value2) {
            addCriterion("business_license not between", value1, value2, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andLegalPersonNameIsNull() {
            addCriterion("legal_person_name is null");
            return (Criteria) this;
        }

        public Criteria andLegalPersonNameIsNotNull() {
            addCriterion("legal_person_name is not null");
            return (Criteria) this;
        }

        public Criteria andLegalPersonNameEqualTo(String value) {
            addCriterion("legal_person_name =", value, "legalPersonName");
            return (Criteria) this;
        }

        public Criteria andLegalPersonNameNotEqualTo(String value) {
            addCriterion("legal_person_name <>", value, "legalPersonName");
            return (Criteria) this;
        }

        public Criteria andLegalPersonNameGreaterThan(String value) {
            addCriterion("legal_person_name >", value, "legalPersonName");
            return (Criteria) this;
        }

        public Criteria andLegalPersonNameGreaterThanOrEqualTo(String value) {
            addCriterion("legal_person_name >=", value, "legalPersonName");
            return (Criteria) this;
        }

        public Criteria andLegalPersonNameLessThan(String value) {
            addCriterion("legal_person_name <", value, "legalPersonName");
            return (Criteria) this;
        }

        public Criteria andLegalPersonNameLessThanOrEqualTo(String value) {
            addCriterion("legal_person_name <=", value, "legalPersonName");
            return (Criteria) this;
        }

        public Criteria andLegalPersonNameLike(String value) {
            addCriterion("legal_person_name like", value, "legalPersonName");
            return (Criteria) this;
        }

        public Criteria andLegalPersonNameNotLike(String value) {
            addCriterion("legal_person_name not like", value, "legalPersonName");
            return (Criteria) this;
        }

        public Criteria andLegalPersonNameIn(List<String> values) {
            addCriterion("legal_person_name in", values, "legalPersonName");
            return (Criteria) this;
        }

        public Criteria andLegalPersonNameNotIn(List<String> values) {
            addCriterion("legal_person_name not in", values, "legalPersonName");
            return (Criteria) this;
        }

        public Criteria andLegalPersonNameBetween(String value1, String value2) {
            addCriterion("legal_person_name between", value1, value2, "legalPersonName");
            return (Criteria) this;
        }

        public Criteria andLegalPersonNameNotBetween(String value1, String value2) {
            addCriterion("legal_person_name not between", value1, value2, "legalPersonName");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdcardIsNull() {
            addCriterion("legal_person_idcard is null");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdcardIsNotNull() {
            addCriterion("legal_person_idcard is not null");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdcardEqualTo(String value) {
            addCriterion("legal_person_idcard =", value, "legalPersonIdcard");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdcardNotEqualTo(String value) {
            addCriterion("legal_person_idcard <>", value, "legalPersonIdcard");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdcardGreaterThan(String value) {
            addCriterion("legal_person_idcard >", value, "legalPersonIdcard");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdcardGreaterThanOrEqualTo(String value) {
            addCriterion("legal_person_idcard >=", value, "legalPersonIdcard");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdcardLessThan(String value) {
            addCriterion("legal_person_idcard <", value, "legalPersonIdcard");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdcardLessThanOrEqualTo(String value) {
            addCriterion("legal_person_idcard <=", value, "legalPersonIdcard");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdcardLike(String value) {
            addCriterion("legal_person_idcard like", value, "legalPersonIdcard");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdcardNotLike(String value) {
            addCriterion("legal_person_idcard not like", value, "legalPersonIdcard");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdcardIn(List<String> values) {
            addCriterion("legal_person_idcard in", values, "legalPersonIdcard");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdcardNotIn(List<String> values) {
            addCriterion("legal_person_idcard not in", values, "legalPersonIdcard");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdcardBetween(String value1, String value2) {
            addCriterion("legal_person_idcard between", value1, value2, "legalPersonIdcard");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIdcardNotBetween(String value1, String value2) {
            addCriterion("legal_person_idcard not between", value1, value2, "legalPersonIdcard");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalIsNull() {
            addCriterion("registered_capital is null");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalIsNotNull() {
            addCriterion("registered_capital is not null");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalEqualTo(String value) {
            addCriterion("registered_capital =", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalNotEqualTo(String value) {
            addCriterion("registered_capital <>", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalGreaterThan(String value) {
            addCriterion("registered_capital >", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalGreaterThanOrEqualTo(String value) {
            addCriterion("registered_capital >=", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalLessThan(String value) {
            addCriterion("registered_capital <", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalLessThanOrEqualTo(String value) {
            addCriterion("registered_capital <=", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalLike(String value) {
            addCriterion("registered_capital like", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalNotLike(String value) {
            addCriterion("registered_capital not like", value, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalIn(List<String> values) {
            addCriterion("registered_capital in", values, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalNotIn(List<String> values) {
            addCriterion("registered_capital not in", values, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalBetween(String value1, String value2) {
            addCriterion("registered_capital between", value1, value2, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalNotBetween(String value1, String value2) {
            addCriterion("registered_capital not between", value1, value2, "registeredCapital");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andContactPersonIsNull() {
            addCriterion("contact_person is null");
            return (Criteria) this;
        }

        public Criteria andContactPersonIsNotNull() {
            addCriterion("contact_person is not null");
            return (Criteria) this;
        }

        public Criteria andContactPersonEqualTo(String value) {
            addCriterion("contact_person =", value, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonNotEqualTo(String value) {
            addCriterion("contact_person <>", value, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonGreaterThan(String value) {
            addCriterion("contact_person >", value, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonGreaterThanOrEqualTo(String value) {
            addCriterion("contact_person >=", value, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonLessThan(String value) {
            addCriterion("contact_person <", value, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonLessThanOrEqualTo(String value) {
            addCriterion("contact_person <=", value, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonLike(String value) {
            addCriterion("contact_person like", value, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonNotLike(String value) {
            addCriterion("contact_person not like", value, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonIn(List<String> values) {
            addCriterion("contact_person in", values, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonNotIn(List<String> values) {
            addCriterion("contact_person not in", values, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonBetween(String value1, String value2) {
            addCriterion("contact_person between", value1, value2, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonNotBetween(String value1, String value2) {
            addCriterion("contact_person not between", value1, value2, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andFaxIsNull() {
            addCriterion("fax is null");
            return (Criteria) this;
        }

        public Criteria andFaxIsNotNull() {
            addCriterion("fax is not null");
            return (Criteria) this;
        }

        public Criteria andFaxEqualTo(String value) {
            addCriterion("fax =", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotEqualTo(String value) {
            addCriterion("fax <>", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxGreaterThan(String value) {
            addCriterion("fax >", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxGreaterThanOrEqualTo(String value) {
            addCriterion("fax >=", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLessThan(String value) {
            addCriterion("fax <", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLessThanOrEqualTo(String value) {
            addCriterion("fax <=", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLike(String value) {
            addCriterion("fax like", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotLike(String value) {
            addCriterion("fax not like", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxIn(List<String> values) {
            addCriterion("fax in", values, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotIn(List<String> values) {
            addCriterion("fax not in", values, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxBetween(String value1, String value2) {
            addCriterion("fax between", value1, value2, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotBetween(String value1, String value2) {
            addCriterion("fax not between", value1, value2, "fax");
            return (Criteria) this;
        }

        public Criteria andCountryCodeIsNull() {
            addCriterion("country_code is null");
            return (Criteria) this;
        }

        public Criteria andCountryCodeIsNotNull() {
            addCriterion("country_code is not null");
            return (Criteria) this;
        }

        public Criteria andCountryCodeEqualTo(String value) {
            addCriterion("country_code =", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeNotEqualTo(String value) {
            addCriterion("country_code <>", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeGreaterThan(String value) {
            addCriterion("country_code >", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeGreaterThanOrEqualTo(String value) {
            addCriterion("country_code >=", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeLessThan(String value) {
            addCriterion("country_code <", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeLessThanOrEqualTo(String value) {
            addCriterion("country_code <=", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeLike(String value) {
            addCriterion("country_code like", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeNotLike(String value) {
            addCriterion("country_code not like", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeIn(List<String> values) {
            addCriterion("country_code in", values, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeNotIn(List<String> values) {
            addCriterion("country_code not in", values, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeBetween(String value1, String value2) {
            addCriterion("country_code between", value1, value2, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeNotBetween(String value1, String value2) {
            addCriterion("country_code not between", value1, value2, "countryCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeIsNull() {
            addCriterion("post_code is null");
            return (Criteria) this;
        }

        public Criteria andPostCodeIsNotNull() {
            addCriterion("post_code is not null");
            return (Criteria) this;
        }

        public Criteria andPostCodeEqualTo(String value) {
            addCriterion("post_code =", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotEqualTo(String value) {
            addCriterion("post_code <>", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeGreaterThan(String value) {
            addCriterion("post_code >", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeGreaterThanOrEqualTo(String value) {
            addCriterion("post_code >=", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeLessThan(String value) {
            addCriterion("post_code <", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeLessThanOrEqualTo(String value) {
            addCriterion("post_code <=", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeLike(String value) {
            addCriterion("post_code like", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotLike(String value) {
            addCriterion("post_code not like", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeIn(List<String> values) {
            addCriterion("post_code in", values, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotIn(List<String> values) {
            addCriterion("post_code not in", values, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeBetween(String value1, String value2) {
            addCriterion("post_code between", value1, value2, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotBetween(String value1, String value2) {
            addCriterion("post_code not between", value1, value2, "postCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameIsNull() {
            addCriterion("invoice_name is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameIsNotNull() {
            addCriterion("invoice_name is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameEqualTo(String value) {
            addCriterion("invoice_name =", value, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameNotEqualTo(String value) {
            addCriterion("invoice_name <>", value, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameGreaterThan(String value) {
            addCriterion("invoice_name >", value, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_name >=", value, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameLessThan(String value) {
            addCriterion("invoice_name <", value, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameLessThanOrEqualTo(String value) {
            addCriterion("invoice_name <=", value, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameLike(String value) {
            addCriterion("invoice_name like", value, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameNotLike(String value) {
            addCriterion("invoice_name not like", value, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameIn(List<String> values) {
            addCriterion("invoice_name in", values, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameNotIn(List<String> values) {
            addCriterion("invoice_name not in", values, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameBetween(String value1, String value2) {
            addCriterion("invoice_name between", value1, value2, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameNotBetween(String value1, String value2) {
            addCriterion("invoice_name not between", value1, value2, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceName2IsNull() {
            addCriterion("invoice_name2 is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceName2IsNotNull() {
            addCriterion("invoice_name2 is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceName2EqualTo(String value) {
            addCriterion("invoice_name2 =", value, "invoiceName2");
            return (Criteria) this;
        }

        public Criteria andInvoiceName2NotEqualTo(String value) {
            addCriterion("invoice_name2 <>", value, "invoiceName2");
            return (Criteria) this;
        }

        public Criteria andInvoiceName2GreaterThan(String value) {
            addCriterion("invoice_name2 >", value, "invoiceName2");
            return (Criteria) this;
        }

        public Criteria andInvoiceName2GreaterThanOrEqualTo(String value) {
            addCriterion("invoice_name2 >=", value, "invoiceName2");
            return (Criteria) this;
        }

        public Criteria andInvoiceName2LessThan(String value) {
            addCriterion("invoice_name2 <", value, "invoiceName2");
            return (Criteria) this;
        }

        public Criteria andInvoiceName2LessThanOrEqualTo(String value) {
            addCriterion("invoice_name2 <=", value, "invoiceName2");
            return (Criteria) this;
        }

        public Criteria andInvoiceName2Like(String value) {
            addCriterion("invoice_name2 like", value, "invoiceName2");
            return (Criteria) this;
        }

        public Criteria andInvoiceName2NotLike(String value) {
            addCriterion("invoice_name2 not like", value, "invoiceName2");
            return (Criteria) this;
        }

        public Criteria andInvoiceName2In(List<String> values) {
            addCriterion("invoice_name2 in", values, "invoiceName2");
            return (Criteria) this;
        }

        public Criteria andInvoiceName2NotIn(List<String> values) {
            addCriterion("invoice_name2 not in", values, "invoiceName2");
            return (Criteria) this;
        }

        public Criteria andInvoiceName2Between(String value1, String value2) {
            addCriterion("invoice_name2 between", value1, value2, "invoiceName2");
            return (Criteria) this;
        }

        public Criteria andInvoiceName2NotBetween(String value1, String value2) {
            addCriterion("invoice_name2 not between", value1, value2, "invoiceName2");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceIsNull() {
            addCriterion("region_province is null");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceIsNotNull() {
            addCriterion("region_province is not null");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceEqualTo(String value) {
            addCriterion("region_province =", value, "regionProvince");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceNotEqualTo(String value) {
            addCriterion("region_province <>", value, "regionProvince");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceGreaterThan(String value) {
            addCriterion("region_province >", value, "regionProvince");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("region_province >=", value, "regionProvince");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceLessThan(String value) {
            addCriterion("region_province <", value, "regionProvince");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceLessThanOrEqualTo(String value) {
            addCriterion("region_province <=", value, "regionProvince");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceLike(String value) {
            addCriterion("region_province like", value, "regionProvince");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceNotLike(String value) {
            addCriterion("region_province not like", value, "regionProvince");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceIn(List<String> values) {
            addCriterion("region_province in", values, "regionProvince");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceNotIn(List<String> values) {
            addCriterion("region_province not in", values, "regionProvince");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceBetween(String value1, String value2) {
            addCriterion("region_province between", value1, value2, "regionProvince");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceNotBetween(String value1, String value2) {
            addCriterion("region_province not between", value1, value2, "regionProvince");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceNameIsNull() {
            addCriterion("region_province_name is null");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceNameIsNotNull() {
            addCriterion("region_province_name is not null");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceNameEqualTo(String value) {
            addCriterion("region_province_name =", value, "regionProvinceName");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceNameNotEqualTo(String value) {
            addCriterion("region_province_name <>", value, "regionProvinceName");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceNameGreaterThan(String value) {
            addCriterion("region_province_name >", value, "regionProvinceName");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceNameGreaterThanOrEqualTo(String value) {
            addCriterion("region_province_name >=", value, "regionProvinceName");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceNameLessThan(String value) {
            addCriterion("region_province_name <", value, "regionProvinceName");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceNameLessThanOrEqualTo(String value) {
            addCriterion("region_province_name <=", value, "regionProvinceName");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceNameLike(String value) {
            addCriterion("region_province_name like", value, "regionProvinceName");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceNameNotLike(String value) {
            addCriterion("region_province_name not like", value, "regionProvinceName");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceNameIn(List<String> values) {
            addCriterion("region_province_name in", values, "regionProvinceName");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceNameNotIn(List<String> values) {
            addCriterion("region_province_name not in", values, "regionProvinceName");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceNameBetween(String value1, String value2) {
            addCriterion("region_province_name between", value1, value2, "regionProvinceName");
            return (Criteria) this;
        }

        public Criteria andRegionProvinceNameNotBetween(String value1, String value2) {
            addCriterion("region_province_name not between", value1, value2, "regionProvinceName");
            return (Criteria) this;
        }

        public Criteria andRegionCityIsNull() {
            addCriterion("region_city is null");
            return (Criteria) this;
        }

        public Criteria andRegionCityIsNotNull() {
            addCriterion("region_city is not null");
            return (Criteria) this;
        }

        public Criteria andRegionCityEqualTo(String value) {
            addCriterion("region_city =", value, "regionCity");
            return (Criteria) this;
        }

        public Criteria andRegionCityNotEqualTo(String value) {
            addCriterion("region_city <>", value, "regionCity");
            return (Criteria) this;
        }

        public Criteria andRegionCityGreaterThan(String value) {
            addCriterion("region_city >", value, "regionCity");
            return (Criteria) this;
        }

        public Criteria andRegionCityGreaterThanOrEqualTo(String value) {
            addCriterion("region_city >=", value, "regionCity");
            return (Criteria) this;
        }

        public Criteria andRegionCityLessThan(String value) {
            addCriterion("region_city <", value, "regionCity");
            return (Criteria) this;
        }

        public Criteria andRegionCityLessThanOrEqualTo(String value) {
            addCriterion("region_city <=", value, "regionCity");
            return (Criteria) this;
        }

        public Criteria andRegionCityLike(String value) {
            addCriterion("region_city like", value, "regionCity");
            return (Criteria) this;
        }

        public Criteria andRegionCityNotLike(String value) {
            addCriterion("region_city not like", value, "regionCity");
            return (Criteria) this;
        }

        public Criteria andRegionCityIn(List<String> values) {
            addCriterion("region_city in", values, "regionCity");
            return (Criteria) this;
        }

        public Criteria andRegionCityNotIn(List<String> values) {
            addCriterion("region_city not in", values, "regionCity");
            return (Criteria) this;
        }

        public Criteria andRegionCityBetween(String value1, String value2) {
            addCriterion("region_city between", value1, value2, "regionCity");
            return (Criteria) this;
        }

        public Criteria andRegionCityNotBetween(String value1, String value2) {
            addCriterion("region_city not between", value1, value2, "regionCity");
            return (Criteria) this;
        }

        public Criteria andRegionAreaIsNull() {
            addCriterion("region_area is null");
            return (Criteria) this;
        }

        public Criteria andRegionAreaIsNotNull() {
            addCriterion("region_area is not null");
            return (Criteria) this;
        }

        public Criteria andRegionAreaEqualTo(String value) {
            addCriterion("region_area =", value, "regionArea");
            return (Criteria) this;
        }

        public Criteria andRegionAreaNotEqualTo(String value) {
            addCriterion("region_area <>", value, "regionArea");
            return (Criteria) this;
        }

        public Criteria andRegionAreaGreaterThan(String value) {
            addCriterion("region_area >", value, "regionArea");
            return (Criteria) this;
        }

        public Criteria andRegionAreaGreaterThanOrEqualTo(String value) {
            addCriterion("region_area >=", value, "regionArea");
            return (Criteria) this;
        }

        public Criteria andRegionAreaLessThan(String value) {
            addCriterion("region_area <", value, "regionArea");
            return (Criteria) this;
        }

        public Criteria andRegionAreaLessThanOrEqualTo(String value) {
            addCriterion("region_area <=", value, "regionArea");
            return (Criteria) this;
        }

        public Criteria andRegionAreaLike(String value) {
            addCriterion("region_area like", value, "regionArea");
            return (Criteria) this;
        }

        public Criteria andRegionAreaNotLike(String value) {
            addCriterion("region_area not like", value, "regionArea");
            return (Criteria) this;
        }

        public Criteria andRegionAreaIn(List<String> values) {
            addCriterion("region_area in", values, "regionArea");
            return (Criteria) this;
        }

        public Criteria andRegionAreaNotIn(List<String> values) {
            addCriterion("region_area not in", values, "regionArea");
            return (Criteria) this;
        }

        public Criteria andRegionAreaBetween(String value1, String value2) {
            addCriterion("region_area between", value1, value2, "regionArea");
            return (Criteria) this;
        }

        public Criteria andRegionAreaNotBetween(String value1, String value2) {
            addCriterion("region_area not between", value1, value2, "regionArea");
            return (Criteria) this;
        }

        public Criteria andAddressDetailsIsNull() {
            addCriterion("address_details is null");
            return (Criteria) this;
        }

        public Criteria andAddressDetailsIsNotNull() {
            addCriterion("address_details is not null");
            return (Criteria) this;
        }

        public Criteria andAddressDetailsEqualTo(String value) {
            addCriterion("address_details =", value, "addressDetails");
            return (Criteria) this;
        }

        public Criteria andAddressDetailsNotEqualTo(String value) {
            addCriterion("address_details <>", value, "addressDetails");
            return (Criteria) this;
        }

        public Criteria andAddressDetailsGreaterThan(String value) {
            addCriterion("address_details >", value, "addressDetails");
            return (Criteria) this;
        }

        public Criteria andAddressDetailsGreaterThanOrEqualTo(String value) {
            addCriterion("address_details >=", value, "addressDetails");
            return (Criteria) this;
        }

        public Criteria andAddressDetailsLessThan(String value) {
            addCriterion("address_details <", value, "addressDetails");
            return (Criteria) this;
        }

        public Criteria andAddressDetailsLessThanOrEqualTo(String value) {
            addCriterion("address_details <=", value, "addressDetails");
            return (Criteria) this;
        }

        public Criteria andAddressDetailsLike(String value) {
            addCriterion("address_details like", value, "addressDetails");
            return (Criteria) this;
        }

        public Criteria andAddressDetailsNotLike(String value) {
            addCriterion("address_details not like", value, "addressDetails");
            return (Criteria) this;
        }

        public Criteria andAddressDetailsIn(List<String> values) {
            addCriterion("address_details in", values, "addressDetails");
            return (Criteria) this;
        }

        public Criteria andAddressDetailsNotIn(List<String> values) {
            addCriterion("address_details not in", values, "addressDetails");
            return (Criteria) this;
        }

        public Criteria andAddressDetailsBetween(String value1, String value2) {
            addCriterion("address_details between", value1, value2, "addressDetails");
            return (Criteria) this;
        }

        public Criteria andAddressDetailsNotBetween(String value1, String value2) {
            addCriterion("address_details not between", value1, value2, "addressDetails");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNull() {
            addCriterion("longitude is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNotNull() {
            addCriterion("longitude is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeEqualTo(BigDecimal value) {
            addCriterion("longitude =", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotEqualTo(BigDecimal value) {
            addCriterion("longitude <>", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThan(BigDecimal value) {
            addCriterion("longitude >", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("longitude >=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThan(BigDecimal value) {
            addCriterion("longitude <", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("longitude <=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIn(List<BigDecimal> values) {
            addCriterion("longitude in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotIn(List<BigDecimal> values) {
            addCriterion("longitude not in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("longitude between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("longitude not between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNull() {
            addCriterion("latitude is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("latitude is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(BigDecimal value) {
            addCriterion("latitude =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(BigDecimal value) {
            addCriterion("latitude <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(BigDecimal value) {
            addCriterion("latitude >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("latitude >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(BigDecimal value) {
            addCriterion("latitude <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("latitude <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<BigDecimal> values) {
            addCriterion("latitude in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<BigDecimal> values) {
            addCriterion("latitude not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("latitude between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("latitude not between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberIsNull() {
            addCriterion("bank_account_number is null");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberIsNotNull() {
            addCriterion("bank_account_number is not null");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberEqualTo(String value) {
            addCriterion("bank_account_number =", value, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberNotEqualTo(String value) {
            addCriterion("bank_account_number <>", value, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberGreaterThan(String value) {
            addCriterion("bank_account_number >", value, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberGreaterThanOrEqualTo(String value) {
            addCriterion("bank_account_number >=", value, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberLessThan(String value) {
            addCriterion("bank_account_number <", value, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberLessThanOrEqualTo(String value) {
            addCriterion("bank_account_number <=", value, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberLike(String value) {
            addCriterion("bank_account_number like", value, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberNotLike(String value) {
            addCriterion("bank_account_number not like", value, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberIn(List<String> values) {
            addCriterion("bank_account_number in", values, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberNotIn(List<String> values) {
            addCriterion("bank_account_number not in", values, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberBetween(String value1, String value2) {
            addCriterion("bank_account_number between", value1, value2, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberNotBetween(String value1, String value2) {
            addCriterion("bank_account_number not between", value1, value2, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameIsNull() {
            addCriterion("bank_account_name is null");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameIsNotNull() {
            addCriterion("bank_account_name is not null");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameEqualTo(String value) {
            addCriterion("bank_account_name =", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameNotEqualTo(String value) {
            addCriterion("bank_account_name <>", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameGreaterThan(String value) {
            addCriterion("bank_account_name >", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("bank_account_name >=", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameLessThan(String value) {
            addCriterion("bank_account_name <", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameLessThanOrEqualTo(String value) {
            addCriterion("bank_account_name <=", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameLike(String value) {
            addCriterion("bank_account_name like", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameNotLike(String value) {
            addCriterion("bank_account_name not like", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameIn(List<String> values) {
            addCriterion("bank_account_name in", values, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameNotIn(List<String> values) {
            addCriterion("bank_account_name not in", values, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameBetween(String value1, String value2) {
            addCriterion("bank_account_name between", value1, value2, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameNotBetween(String value1, String value2) {
            addCriterion("bank_account_name not between", value1, value2, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNull() {
            addCriterion("bank_name is null");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNotNull() {
            addCriterion("bank_name is not null");
            return (Criteria) this;
        }

        public Criteria andBankNameEqualTo(String value) {
            addCriterion("bank_name =", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotEqualTo(String value) {
            addCriterion("bank_name <>", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThan(String value) {
            addCriterion("bank_name >", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("bank_name >=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThan(String value) {
            addCriterion("bank_name <", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThanOrEqualTo(String value) {
            addCriterion("bank_name <=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLike(String value) {
            addCriterion("bank_name like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotLike(String value) {
            addCriterion("bank_name not like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameIn(List<String> values) {
            addCriterion("bank_name in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotIn(List<String> values) {
            addCriterion("bank_name not in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameBetween(String value1, String value2) {
            addCriterion("bank_name between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotBetween(String value1, String value2) {
            addCriterion("bank_name not between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNumberIsNull() {
            addCriterion("bank_number is null");
            return (Criteria) this;
        }

        public Criteria andBankNumberIsNotNull() {
            addCriterion("bank_number is not null");
            return (Criteria) this;
        }

        public Criteria andBankNumberEqualTo(String value) {
            addCriterion("bank_number =", value, "bankNumber");
            return (Criteria) this;
        }

        public Criteria andBankNumberNotEqualTo(String value) {
            addCriterion("bank_number <>", value, "bankNumber");
            return (Criteria) this;
        }

        public Criteria andBankNumberGreaterThan(String value) {
            addCriterion("bank_number >", value, "bankNumber");
            return (Criteria) this;
        }

        public Criteria andBankNumberGreaterThanOrEqualTo(String value) {
            addCriterion("bank_number >=", value, "bankNumber");
            return (Criteria) this;
        }

        public Criteria andBankNumberLessThan(String value) {
            addCriterion("bank_number <", value, "bankNumber");
            return (Criteria) this;
        }

        public Criteria andBankNumberLessThanOrEqualTo(String value) {
            addCriterion("bank_number <=", value, "bankNumber");
            return (Criteria) this;
        }

        public Criteria andBankNumberLike(String value) {
            addCriterion("bank_number like", value, "bankNumber");
            return (Criteria) this;
        }

        public Criteria andBankNumberNotLike(String value) {
            addCriterion("bank_number not like", value, "bankNumber");
            return (Criteria) this;
        }

        public Criteria andBankNumberIn(List<String> values) {
            addCriterion("bank_number in", values, "bankNumber");
            return (Criteria) this;
        }

        public Criteria andBankNumberNotIn(List<String> values) {
            addCriterion("bank_number not in", values, "bankNumber");
            return (Criteria) this;
        }

        public Criteria andBankNumberBetween(String value1, String value2) {
            addCriterion("bank_number between", value1, value2, "bankNumber");
            return (Criteria) this;
        }

        public Criteria andBankNumberNotBetween(String value1, String value2) {
            addCriterion("bank_number not between", value1, value2, "bankNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumber2IsNull() {
            addCriterion("bank_account_number2 is null");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumber2IsNotNull() {
            addCriterion("bank_account_number2 is not null");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumber2EqualTo(String value) {
            addCriterion("bank_account_number2 =", value, "bankAccountNumber2");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumber2NotEqualTo(String value) {
            addCriterion("bank_account_number2 <>", value, "bankAccountNumber2");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumber2GreaterThan(String value) {
            addCriterion("bank_account_number2 >", value, "bankAccountNumber2");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumber2GreaterThanOrEqualTo(String value) {
            addCriterion("bank_account_number2 >=", value, "bankAccountNumber2");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumber2LessThan(String value) {
            addCriterion("bank_account_number2 <", value, "bankAccountNumber2");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumber2LessThanOrEqualTo(String value) {
            addCriterion("bank_account_number2 <=", value, "bankAccountNumber2");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumber2Like(String value) {
            addCriterion("bank_account_number2 like", value, "bankAccountNumber2");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumber2NotLike(String value) {
            addCriterion("bank_account_number2 not like", value, "bankAccountNumber2");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumber2In(List<String> values) {
            addCriterion("bank_account_number2 in", values, "bankAccountNumber2");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumber2NotIn(List<String> values) {
            addCriterion("bank_account_number2 not in", values, "bankAccountNumber2");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumber2Between(String value1, String value2) {
            addCriterion("bank_account_number2 between", value1, value2, "bankAccountNumber2");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumber2NotBetween(String value1, String value2) {
            addCriterion("bank_account_number2 not between", value1, value2, "bankAccountNumber2");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeIsNull() {
            addCriterion("company_code is null");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeIsNotNull() {
            addCriterion("company_code is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeEqualTo(String value) {
            addCriterion("company_code =", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeNotEqualTo(String value) {
            addCriterion("company_code <>", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeGreaterThan(String value) {
            addCriterion("company_code >", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("company_code >=", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeLessThan(String value) {
            addCriterion("company_code <", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeLessThanOrEqualTo(String value) {
            addCriterion("company_code <=", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeLike(String value) {
            addCriterion("company_code like", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeNotLike(String value) {
            addCriterion("company_code not like", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeIn(List<String> values) {
            addCriterion("company_code in", values, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeNotIn(List<String> values) {
            addCriterion("company_code not in", values, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeBetween(String value1, String value2) {
            addCriterion("company_code between", value1, value2, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeNotBetween(String value1, String value2) {
            addCriterion("company_code not between", value1, value2, "companyCode");
            return (Criteria) this;
        }

        public Criteria andPaymentTermsIsNull() {
            addCriterion("payment_terms is null");
            return (Criteria) this;
        }

        public Criteria andPaymentTermsIsNotNull() {
            addCriterion("payment_terms is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentTermsEqualTo(String value) {
            addCriterion("payment_terms =", value, "paymentTerms");
            return (Criteria) this;
        }

        public Criteria andPaymentTermsNotEqualTo(String value) {
            addCriterion("payment_terms <>", value, "paymentTerms");
            return (Criteria) this;
        }

        public Criteria andPaymentTermsGreaterThan(String value) {
            addCriterion("payment_terms >", value, "paymentTerms");
            return (Criteria) this;
        }

        public Criteria andPaymentTermsGreaterThanOrEqualTo(String value) {
            addCriterion("payment_terms >=", value, "paymentTerms");
            return (Criteria) this;
        }

        public Criteria andPaymentTermsLessThan(String value) {
            addCriterion("payment_terms <", value, "paymentTerms");
            return (Criteria) this;
        }

        public Criteria andPaymentTermsLessThanOrEqualTo(String value) {
            addCriterion("payment_terms <=", value, "paymentTerms");
            return (Criteria) this;
        }

        public Criteria andPaymentTermsLike(String value) {
            addCriterion("payment_terms like", value, "paymentTerms");
            return (Criteria) this;
        }

        public Criteria andPaymentTermsNotLike(String value) {
            addCriterion("payment_terms not like", value, "paymentTerms");
            return (Criteria) this;
        }

        public Criteria andPaymentTermsIn(List<String> values) {
            addCriterion("payment_terms in", values, "paymentTerms");
            return (Criteria) this;
        }

        public Criteria andPaymentTermsNotIn(List<String> values) {
            addCriterion("payment_terms not in", values, "paymentTerms");
            return (Criteria) this;
        }

        public Criteria andPaymentTermsBetween(String value1, String value2) {
            addCriterion("payment_terms between", value1, value2, "paymentTerms");
            return (Criteria) this;
        }

        public Criteria andPaymentTermsNotBetween(String value1, String value2) {
            addCriterion("payment_terms not between", value1, value2, "paymentTerms");
            return (Criteria) this;
        }

        public Criteria andPayModeIsNull() {
            addCriterion("pay_mode is null");
            return (Criteria) this;
        }

        public Criteria andPayModeIsNotNull() {
            addCriterion("pay_mode is not null");
            return (Criteria) this;
        }

        public Criteria andPayModeEqualTo(String value) {
            addCriterion("pay_mode =", value, "payMode");
            return (Criteria) this;
        }

        public Criteria andPayModeNotEqualTo(String value) {
            addCriterion("pay_mode <>", value, "payMode");
            return (Criteria) this;
        }

        public Criteria andPayModeGreaterThan(String value) {
            addCriterion("pay_mode >", value, "payMode");
            return (Criteria) this;
        }

        public Criteria andPayModeGreaterThanOrEqualTo(String value) {
            addCriterion("pay_mode >=", value, "payMode");
            return (Criteria) this;
        }

        public Criteria andPayModeLessThan(String value) {
            addCriterion("pay_mode <", value, "payMode");
            return (Criteria) this;
        }

        public Criteria andPayModeLessThanOrEqualTo(String value) {
            addCriterion("pay_mode <=", value, "payMode");
            return (Criteria) this;
        }

        public Criteria andPayModeLike(String value) {
            addCriterion("pay_mode like", value, "payMode");
            return (Criteria) this;
        }

        public Criteria andPayModeNotLike(String value) {
            addCriterion("pay_mode not like", value, "payMode");
            return (Criteria) this;
        }

        public Criteria andPayModeIn(List<String> values) {
            addCriterion("pay_mode in", values, "payMode");
            return (Criteria) this;
        }

        public Criteria andPayModeNotIn(List<String> values) {
            addCriterion("pay_mode not in", values, "payMode");
            return (Criteria) this;
        }

        public Criteria andPayModeBetween(String value1, String value2) {
            addCriterion("pay_mode between", value1, value2, "payMode");
            return (Criteria) this;
        }

        public Criteria andPayModeNotBetween(String value1, String value2) {
            addCriterion("pay_mode not between", value1, value2, "payMode");
            return (Criteria) this;
        }

        public Criteria andPaymentDaysIsNull() {
            addCriterion("payment_days is null");
            return (Criteria) this;
        }

        public Criteria andPaymentDaysIsNotNull() {
            addCriterion("payment_days is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentDaysEqualTo(String value) {
            addCriterion("payment_days =", value, "paymentDays");
            return (Criteria) this;
        }

        public Criteria andPaymentDaysNotEqualTo(String value) {
            addCriterion("payment_days <>", value, "paymentDays");
            return (Criteria) this;
        }

        public Criteria andPaymentDaysGreaterThan(String value) {
            addCriterion("payment_days >", value, "paymentDays");
            return (Criteria) this;
        }

        public Criteria andPaymentDaysGreaterThanOrEqualTo(String value) {
            addCriterion("payment_days >=", value, "paymentDays");
            return (Criteria) this;
        }

        public Criteria andPaymentDaysLessThan(String value) {
            addCriterion("payment_days <", value, "paymentDays");
            return (Criteria) this;
        }

        public Criteria andPaymentDaysLessThanOrEqualTo(String value) {
            addCriterion("payment_days <=", value, "paymentDays");
            return (Criteria) this;
        }

        public Criteria andPaymentDaysLike(String value) {
            addCriterion("payment_days like", value, "paymentDays");
            return (Criteria) this;
        }

        public Criteria andPaymentDaysNotLike(String value) {
            addCriterion("payment_days not like", value, "paymentDays");
            return (Criteria) this;
        }

        public Criteria andPaymentDaysIn(List<String> values) {
            addCriterion("payment_days in", values, "paymentDays");
            return (Criteria) this;
        }

        public Criteria andPaymentDaysNotIn(List<String> values) {
            addCriterion("payment_days not in", values, "paymentDays");
            return (Criteria) this;
        }

        public Criteria andPaymentDaysBetween(String value1, String value2) {
            addCriterion("payment_days between", value1, value2, "paymentDays");
            return (Criteria) this;
        }

        public Criteria andPaymentDaysNotBetween(String value1, String value2) {
            addCriterion("payment_days not between", value1, value2, "paymentDays");
            return (Criteria) this;
        }

        public Criteria andPlanSalesAmountIsNull() {
            addCriterion("plan_sales_amount is null");
            return (Criteria) this;
        }

        public Criteria andPlanSalesAmountIsNotNull() {
            addCriterion("plan_sales_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPlanSalesAmountEqualTo(BigDecimal value) {
            addCriterion("plan_sales_amount =", value, "planSalesAmount");
            return (Criteria) this;
        }

        public Criteria andPlanSalesAmountNotEqualTo(BigDecimal value) {
            addCriterion("plan_sales_amount <>", value, "planSalesAmount");
            return (Criteria) this;
        }

        public Criteria andPlanSalesAmountGreaterThan(BigDecimal value) {
            addCriterion("plan_sales_amount >", value, "planSalesAmount");
            return (Criteria) this;
        }

        public Criteria andPlanSalesAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("plan_sales_amount >=", value, "planSalesAmount");
            return (Criteria) this;
        }

        public Criteria andPlanSalesAmountLessThan(BigDecimal value) {
            addCriterion("plan_sales_amount <", value, "planSalesAmount");
            return (Criteria) this;
        }

        public Criteria andPlanSalesAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("plan_sales_amount <=", value, "planSalesAmount");
            return (Criteria) this;
        }

        public Criteria andPlanSalesAmountIn(List<BigDecimal> values) {
            addCriterion("plan_sales_amount in", values, "planSalesAmount");
            return (Criteria) this;
        }

        public Criteria andPlanSalesAmountNotIn(List<BigDecimal> values) {
            addCriterion("plan_sales_amount not in", values, "planSalesAmount");
            return (Criteria) this;
        }

        public Criteria andPlanSalesAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("plan_sales_amount between", value1, value2, "planSalesAmount");
            return (Criteria) this;
        }

        public Criteria andPlanSalesAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("plan_sales_amount not between", value1, value2, "planSalesAmount");
            return (Criteria) this;
        }

        public Criteria andWrittenAmountIsNull() {
            addCriterion("written_amount is null");
            return (Criteria) this;
        }

        public Criteria andWrittenAmountIsNotNull() {
            addCriterion("written_amount is not null");
            return (Criteria) this;
        }

        public Criteria andWrittenAmountEqualTo(BigDecimal value) {
            addCriterion("written_amount =", value, "writtenAmount");
            return (Criteria) this;
        }

        public Criteria andWrittenAmountNotEqualTo(BigDecimal value) {
            addCriterion("written_amount <>", value, "writtenAmount");
            return (Criteria) this;
        }

        public Criteria andWrittenAmountGreaterThan(BigDecimal value) {
            addCriterion("written_amount >", value, "writtenAmount");
            return (Criteria) this;
        }

        public Criteria andWrittenAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("written_amount >=", value, "writtenAmount");
            return (Criteria) this;
        }

        public Criteria andWrittenAmountLessThan(BigDecimal value) {
            addCriterion("written_amount <", value, "writtenAmount");
            return (Criteria) this;
        }

        public Criteria andWrittenAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("written_amount <=", value, "writtenAmount");
            return (Criteria) this;
        }

        public Criteria andWrittenAmountIn(List<BigDecimal> values) {
            addCriterion("written_amount in", values, "writtenAmount");
            return (Criteria) this;
        }

        public Criteria andWrittenAmountNotIn(List<BigDecimal> values) {
            addCriterion("written_amount not in", values, "writtenAmount");
            return (Criteria) this;
        }

        public Criteria andWrittenAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("written_amount between", value1, value2, "writtenAmount");
            return (Criteria) this;
        }

        public Criteria andWrittenAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("written_amount not between", value1, value2, "writtenAmount");
            return (Criteria) this;
        }

        public Criteria andB2bShopCodeIsNull() {
            addCriterion("b2b_shop_code is null");
            return (Criteria) this;
        }

        public Criteria andB2bShopCodeIsNotNull() {
            addCriterion("b2b_shop_code is not null");
            return (Criteria) this;
        }

        public Criteria andB2bShopCodeEqualTo(String value) {
            addCriterion("b2b_shop_code =", value, "b2bShopCode");
            return (Criteria) this;
        }

        public Criteria andB2bShopCodeNotEqualTo(String value) {
            addCriterion("b2b_shop_code <>", value, "b2bShopCode");
            return (Criteria) this;
        }

        public Criteria andB2bShopCodeGreaterThan(String value) {
            addCriterion("b2b_shop_code >", value, "b2bShopCode");
            return (Criteria) this;
        }

        public Criteria andB2bShopCodeGreaterThanOrEqualTo(String value) {
            addCriterion("b2b_shop_code >=", value, "b2bShopCode");
            return (Criteria) this;
        }

        public Criteria andB2bShopCodeLessThan(String value) {
            addCriterion("b2b_shop_code <", value, "b2bShopCode");
            return (Criteria) this;
        }

        public Criteria andB2bShopCodeLessThanOrEqualTo(String value) {
            addCriterion("b2b_shop_code <=", value, "b2bShopCode");
            return (Criteria) this;
        }

        public Criteria andB2bShopCodeLike(String value) {
            addCriterion("b2b_shop_code like", value, "b2bShopCode");
            return (Criteria) this;
        }

        public Criteria andB2bShopCodeNotLike(String value) {
            addCriterion("b2b_shop_code not like", value, "b2bShopCode");
            return (Criteria) this;
        }

        public Criteria andB2bShopCodeIn(List<String> values) {
            addCriterion("b2b_shop_code in", values, "b2bShopCode");
            return (Criteria) this;
        }

        public Criteria andB2bShopCodeNotIn(List<String> values) {
            addCriterion("b2b_shop_code not in", values, "b2bShopCode");
            return (Criteria) this;
        }

        public Criteria andB2bShopCodeBetween(String value1, String value2) {
            addCriterion("b2b_shop_code between", value1, value2, "b2bShopCode");
            return (Criteria) this;
        }

        public Criteria andB2bShopCodeNotBetween(String value1, String value2) {
            addCriterion("b2b_shop_code not between", value1, value2, "b2bShopCode");
            return (Criteria) this;
        }

        public Criteria andB2bShopNameIsNull() {
            addCriterion("b2b_shop_name is null");
            return (Criteria) this;
        }

        public Criteria andB2bShopNameIsNotNull() {
            addCriterion("b2b_shop_name is not null");
            return (Criteria) this;
        }

        public Criteria andB2bShopNameEqualTo(String value) {
            addCriterion("b2b_shop_name =", value, "b2bShopName");
            return (Criteria) this;
        }

        public Criteria andB2bShopNameNotEqualTo(String value) {
            addCriterion("b2b_shop_name <>", value, "b2bShopName");
            return (Criteria) this;
        }

        public Criteria andB2bShopNameGreaterThan(String value) {
            addCriterion("b2b_shop_name >", value, "b2bShopName");
            return (Criteria) this;
        }

        public Criteria andB2bShopNameGreaterThanOrEqualTo(String value) {
            addCriterion("b2b_shop_name >=", value, "b2bShopName");
            return (Criteria) this;
        }

        public Criteria andB2bShopNameLessThan(String value) {
            addCriterion("b2b_shop_name <", value, "b2bShopName");
            return (Criteria) this;
        }

        public Criteria andB2bShopNameLessThanOrEqualTo(String value) {
            addCriterion("b2b_shop_name <=", value, "b2bShopName");
            return (Criteria) this;
        }

        public Criteria andB2bShopNameLike(String value) {
            addCriterion("b2b_shop_name like", value, "b2bShopName");
            return (Criteria) this;
        }

        public Criteria andB2bShopNameNotLike(String value) {
            addCriterion("b2b_shop_name not like", value, "b2bShopName");
            return (Criteria) this;
        }

        public Criteria andB2bShopNameIn(List<String> values) {
            addCriterion("b2b_shop_name in", values, "b2bShopName");
            return (Criteria) this;
        }

        public Criteria andB2bShopNameNotIn(List<String> values) {
            addCriterion("b2b_shop_name not in", values, "b2bShopName");
            return (Criteria) this;
        }

        public Criteria andB2bShopNameBetween(String value1, String value2) {
            addCriterion("b2b_shop_name between", value1, value2, "b2bShopName");
            return (Criteria) this;
        }

        public Criteria andB2bShopNameNotBetween(String value1, String value2) {
            addCriterion("b2b_shop_name not between", value1, value2, "b2bShopName");
            return (Criteria) this;
        }

        public Criteria andSalesUserIdIsNull() {
            addCriterion("sales_user_id is null");
            return (Criteria) this;
        }

        public Criteria andSalesUserIdIsNotNull() {
            addCriterion("sales_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andSalesUserIdEqualTo(Long value) {
            addCriterion("sales_user_id =", value, "salesUserId");
            return (Criteria) this;
        }

        public Criteria andSalesUserIdNotEqualTo(Long value) {
            addCriterion("sales_user_id <>", value, "salesUserId");
            return (Criteria) this;
        }

        public Criteria andSalesUserIdGreaterThan(Long value) {
            addCriterion("sales_user_id >", value, "salesUserId");
            return (Criteria) this;
        }

        public Criteria andSalesUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("sales_user_id >=", value, "salesUserId");
            return (Criteria) this;
        }

        public Criteria andSalesUserIdLessThan(Long value) {
            addCriterion("sales_user_id <", value, "salesUserId");
            return (Criteria) this;
        }

        public Criteria andSalesUserIdLessThanOrEqualTo(Long value) {
            addCriterion("sales_user_id <=", value, "salesUserId");
            return (Criteria) this;
        }

        public Criteria andSalesUserIdIn(List<Long> values) {
            addCriterion("sales_user_id in", values, "salesUserId");
            return (Criteria) this;
        }

        public Criteria andSalesUserIdNotIn(List<Long> values) {
            addCriterion("sales_user_id not in", values, "salesUserId");
            return (Criteria) this;
        }

        public Criteria andSalesUserIdBetween(Long value1, Long value2) {
            addCriterion("sales_user_id between", value1, value2, "salesUserId");
            return (Criteria) this;
        }

        public Criteria andSalesUserIdNotBetween(Long value1, Long value2) {
            addCriterion("sales_user_id not between", value1, value2, "salesUserId");
            return (Criteria) this;
        }

        public Criteria andSalesUserNameIsNull() {
            addCriterion("sales_user_name is null");
            return (Criteria) this;
        }

        public Criteria andSalesUserNameIsNotNull() {
            addCriterion("sales_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andSalesUserNameEqualTo(String value) {
            addCriterion("sales_user_name =", value, "salesUserName");
            return (Criteria) this;
        }

        public Criteria andSalesUserNameNotEqualTo(String value) {
            addCriterion("sales_user_name <>", value, "salesUserName");
            return (Criteria) this;
        }

        public Criteria andSalesUserNameGreaterThan(String value) {
            addCriterion("sales_user_name >", value, "salesUserName");
            return (Criteria) this;
        }

        public Criteria andSalesUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("sales_user_name >=", value, "salesUserName");
            return (Criteria) this;
        }

        public Criteria andSalesUserNameLessThan(String value) {
            addCriterion("sales_user_name <", value, "salesUserName");
            return (Criteria) this;
        }

        public Criteria andSalesUserNameLessThanOrEqualTo(String value) {
            addCriterion("sales_user_name <=", value, "salesUserName");
            return (Criteria) this;
        }

        public Criteria andSalesUserNameLike(String value) {
            addCriterion("sales_user_name like", value, "salesUserName");
            return (Criteria) this;
        }

        public Criteria andSalesUserNameNotLike(String value) {
            addCriterion("sales_user_name not like", value, "salesUserName");
            return (Criteria) this;
        }

        public Criteria andSalesUserNameIn(List<String> values) {
            addCriterion("sales_user_name in", values, "salesUserName");
            return (Criteria) this;
        }

        public Criteria andSalesUserNameNotIn(List<String> values) {
            addCriterion("sales_user_name not in", values, "salesUserName");
            return (Criteria) this;
        }

        public Criteria andSalesUserNameBetween(String value1, String value2) {
            addCriterion("sales_user_name between", value1, value2, "salesUserName");
            return (Criteria) this;
        }

        public Criteria andSalesUserNameNotBetween(String value1, String value2) {
            addCriterion("sales_user_name not between", value1, value2, "salesUserName");
            return (Criteria) this;
        }

        public Criteria andTaxesCategoryIsNull() {
            addCriterion("taxes_category is null");
            return (Criteria) this;
        }

        public Criteria andTaxesCategoryIsNotNull() {
            addCriterion("taxes_category is not null");
            return (Criteria) this;
        }

        public Criteria andTaxesCategoryEqualTo(String value) {
            addCriterion("taxes_category =", value, "taxesCategory");
            return (Criteria) this;
        }

        public Criteria andTaxesCategoryNotEqualTo(String value) {
            addCriterion("taxes_category <>", value, "taxesCategory");
            return (Criteria) this;
        }

        public Criteria andTaxesCategoryGreaterThan(String value) {
            addCriterion("taxes_category >", value, "taxesCategory");
            return (Criteria) this;
        }

        public Criteria andTaxesCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("taxes_category >=", value, "taxesCategory");
            return (Criteria) this;
        }

        public Criteria andTaxesCategoryLessThan(String value) {
            addCriterion("taxes_category <", value, "taxesCategory");
            return (Criteria) this;
        }

        public Criteria andTaxesCategoryLessThanOrEqualTo(String value) {
            addCriterion("taxes_category <=", value, "taxesCategory");
            return (Criteria) this;
        }

        public Criteria andTaxesCategoryLike(String value) {
            addCriterion("taxes_category like", value, "taxesCategory");
            return (Criteria) this;
        }

        public Criteria andTaxesCategoryNotLike(String value) {
            addCriterion("taxes_category not like", value, "taxesCategory");
            return (Criteria) this;
        }

        public Criteria andTaxesCategoryIn(List<String> values) {
            addCriterion("taxes_category in", values, "taxesCategory");
            return (Criteria) this;
        }

        public Criteria andTaxesCategoryNotIn(List<String> values) {
            addCriterion("taxes_category not in", values, "taxesCategory");
            return (Criteria) this;
        }

        public Criteria andTaxesCategoryBetween(String value1, String value2) {
            addCriterion("taxes_category between", value1, value2, "taxesCategory");
            return (Criteria) this;
        }

        public Criteria andTaxesCategoryNotBetween(String value1, String value2) {
            addCriterion("taxes_category not between", value1, value2, "taxesCategory");
            return (Criteria) this;
        }

        public Criteria andSalesProvinceIsNull() {
            addCriterion("sales_province is null");
            return (Criteria) this;
        }

        public Criteria andSalesProvinceIsNotNull() {
            addCriterion("sales_province is not null");
            return (Criteria) this;
        }

        public Criteria andSalesProvinceEqualTo(String value) {
            addCriterion("sales_province =", value, "salesProvince");
            return (Criteria) this;
        }

        public Criteria andSalesProvinceNotEqualTo(String value) {
            addCriterion("sales_province <>", value, "salesProvince");
            return (Criteria) this;
        }

        public Criteria andSalesProvinceGreaterThan(String value) {
            addCriterion("sales_province >", value, "salesProvince");
            return (Criteria) this;
        }

        public Criteria andSalesProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("sales_province >=", value, "salesProvince");
            return (Criteria) this;
        }

        public Criteria andSalesProvinceLessThan(String value) {
            addCriterion("sales_province <", value, "salesProvince");
            return (Criteria) this;
        }

        public Criteria andSalesProvinceLessThanOrEqualTo(String value) {
            addCriterion("sales_province <=", value, "salesProvince");
            return (Criteria) this;
        }

        public Criteria andSalesProvinceLike(String value) {
            addCriterion("sales_province like", value, "salesProvince");
            return (Criteria) this;
        }

        public Criteria andSalesProvinceNotLike(String value) {
            addCriterion("sales_province not like", value, "salesProvince");
            return (Criteria) this;
        }

        public Criteria andSalesProvinceIn(List<String> values) {
            addCriterion("sales_province in", values, "salesProvince");
            return (Criteria) this;
        }

        public Criteria andSalesProvinceNotIn(List<String> values) {
            addCriterion("sales_province not in", values, "salesProvince");
            return (Criteria) this;
        }

        public Criteria andSalesProvinceBetween(String value1, String value2) {
            addCriterion("sales_province between", value1, value2, "salesProvince");
            return (Criteria) this;
        }

        public Criteria andSalesProvinceNotBetween(String value1, String value2) {
            addCriterion("sales_province not between", value1, value2, "salesProvince");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelIsNull() {
            addCriterion("customer_level is null");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelIsNotNull() {
            addCriterion("customer_level is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelEqualTo(String value) {
            addCriterion("customer_level =", value, "customerLevel");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelNotEqualTo(String value) {
            addCriterion("customer_level <>", value, "customerLevel");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelGreaterThan(String value) {
            addCriterion("customer_level >", value, "customerLevel");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelGreaterThanOrEqualTo(String value) {
            addCriterion("customer_level >=", value, "customerLevel");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelLessThan(String value) {
            addCriterion("customer_level <", value, "customerLevel");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelLessThanOrEqualTo(String value) {
            addCriterion("customer_level <=", value, "customerLevel");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelLike(String value) {
            addCriterion("customer_level like", value, "customerLevel");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelNotLike(String value) {
            addCriterion("customer_level not like", value, "customerLevel");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelIn(List<String> values) {
            addCriterion("customer_level in", values, "customerLevel");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelNotIn(List<String> values) {
            addCriterion("customer_level not in", values, "customerLevel");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelBetween(String value1, String value2) {
            addCriterion("customer_level between", value1, value2, "customerLevel");
            return (Criteria) this;
        }

        public Criteria andCustomerLevelNotBetween(String value1, String value2) {
            addCriterion("customer_level not between", value1, value2, "customerLevel");
            return (Criteria) this;
        }

        public Criteria andCreditLimitIsNull() {
            addCriterion("credit_limit is null");
            return (Criteria) this;
        }

        public Criteria andCreditLimitIsNotNull() {
            addCriterion("credit_limit is not null");
            return (Criteria) this;
        }

        public Criteria andCreditLimitEqualTo(BigDecimal value) {
            addCriterion("credit_limit =", value, "creditLimit");
            return (Criteria) this;
        }

        public Criteria andCreditLimitNotEqualTo(BigDecimal value) {
            addCriterion("credit_limit <>", value, "creditLimit");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGreaterThan(BigDecimal value) {
            addCriterion("credit_limit >", value, "creditLimit");
            return (Criteria) this;
        }

        public Criteria andCreditLimitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("credit_limit >=", value, "creditLimit");
            return (Criteria) this;
        }

        public Criteria andCreditLimitLessThan(BigDecimal value) {
            addCriterion("credit_limit <", value, "creditLimit");
            return (Criteria) this;
        }

        public Criteria andCreditLimitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("credit_limit <=", value, "creditLimit");
            return (Criteria) this;
        }

        public Criteria andCreditLimitIn(List<BigDecimal> values) {
            addCriterion("credit_limit in", values, "creditLimit");
            return (Criteria) this;
        }

        public Criteria andCreditLimitNotIn(List<BigDecimal> values) {
            addCriterion("credit_limit not in", values, "creditLimit");
            return (Criteria) this;
        }

        public Criteria andCreditLimitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("credit_limit between", value1, value2, "creditLimit");
            return (Criteria) this;
        }

        public Criteria andCreditLimitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("credit_limit not between", value1, value2, "creditLimit");
            return (Criteria) this;
        }

        public Criteria andTempCreditLimitIsNull() {
            addCriterion("temp_credit_limit is null");
            return (Criteria) this;
        }

        public Criteria andTempCreditLimitIsNotNull() {
            addCriterion("temp_credit_limit is not null");
            return (Criteria) this;
        }

        public Criteria andTempCreditLimitEqualTo(BigDecimal value) {
            addCriterion("temp_credit_limit =", value, "tempCreditLimit");
            return (Criteria) this;
        }

        public Criteria andTempCreditLimitNotEqualTo(BigDecimal value) {
            addCriterion("temp_credit_limit <>", value, "tempCreditLimit");
            return (Criteria) this;
        }

        public Criteria andTempCreditLimitGreaterThan(BigDecimal value) {
            addCriterion("temp_credit_limit >", value, "tempCreditLimit");
            return (Criteria) this;
        }

        public Criteria andTempCreditLimitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("temp_credit_limit >=", value, "tempCreditLimit");
            return (Criteria) this;
        }

        public Criteria andTempCreditLimitLessThan(BigDecimal value) {
            addCriterion("temp_credit_limit <", value, "tempCreditLimit");
            return (Criteria) this;
        }

        public Criteria andTempCreditLimitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("temp_credit_limit <=", value, "tempCreditLimit");
            return (Criteria) this;
        }

        public Criteria andTempCreditLimitIn(List<BigDecimal> values) {
            addCriterion("temp_credit_limit in", values, "tempCreditLimit");
            return (Criteria) this;
        }

        public Criteria andTempCreditLimitNotIn(List<BigDecimal> values) {
            addCriterion("temp_credit_limit not in", values, "tempCreditLimit");
            return (Criteria) this;
        }

        public Criteria andTempCreditLimitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("temp_credit_limit between", value1, value2, "tempCreditLimit");
            return (Criteria) this;
        }

        public Criteria andTempCreditLimitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("temp_credit_limit not between", value1, value2, "tempCreditLimit");
            return (Criteria) this;
        }

        public Criteria andTempBeginTimeIsNull() {
            addCriterion("temp_begin_time is null");
            return (Criteria) this;
        }

        public Criteria andTempBeginTimeIsNotNull() {
            addCriterion("temp_begin_time is not null");
            return (Criteria) this;
        }

        public Criteria andTempBeginTimeEqualTo(Date value) {
            addCriterion("temp_begin_time =", value, "tempBeginTime");
            return (Criteria) this;
        }

        public Criteria andTempBeginTimeNotEqualTo(Date value) {
            addCriterion("temp_begin_time <>", value, "tempBeginTime");
            return (Criteria) this;
        }

        public Criteria andTempBeginTimeGreaterThan(Date value) {
            addCriterion("temp_begin_time >", value, "tempBeginTime");
            return (Criteria) this;
        }

        public Criteria andTempBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("temp_begin_time >=", value, "tempBeginTime");
            return (Criteria) this;
        }

        public Criteria andTempBeginTimeLessThan(Date value) {
            addCriterion("temp_begin_time <", value, "tempBeginTime");
            return (Criteria) this;
        }

        public Criteria andTempBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("temp_begin_time <=", value, "tempBeginTime");
            return (Criteria) this;
        }

        public Criteria andTempBeginTimeIn(List<Date> values) {
            addCriterion("temp_begin_time in", values, "tempBeginTime");
            return (Criteria) this;
        }

        public Criteria andTempBeginTimeNotIn(List<Date> values) {
            addCriterion("temp_begin_time not in", values, "tempBeginTime");
            return (Criteria) this;
        }

        public Criteria andTempBeginTimeBetween(Date value1, Date value2) {
            addCriterion("temp_begin_time between", value1, value2, "tempBeginTime");
            return (Criteria) this;
        }

        public Criteria andTempBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("temp_begin_time not between", value1, value2, "tempBeginTime");
            return (Criteria) this;
        }

        public Criteria andTempEndTimeIsNull() {
            addCriterion("temp_end_time is null");
            return (Criteria) this;
        }

        public Criteria andTempEndTimeIsNotNull() {
            addCriterion("temp_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andTempEndTimeEqualTo(Date value) {
            addCriterion("temp_end_time =", value, "tempEndTime");
            return (Criteria) this;
        }

        public Criteria andTempEndTimeNotEqualTo(Date value) {
            addCriterion("temp_end_time <>", value, "tempEndTime");
            return (Criteria) this;
        }

        public Criteria andTempEndTimeGreaterThan(Date value) {
            addCriterion("temp_end_time >", value, "tempEndTime");
            return (Criteria) this;
        }

        public Criteria andTempEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("temp_end_time >=", value, "tempEndTime");
            return (Criteria) this;
        }

        public Criteria andTempEndTimeLessThan(Date value) {
            addCriterion("temp_end_time <", value, "tempEndTime");
            return (Criteria) this;
        }

        public Criteria andTempEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("temp_end_time <=", value, "tempEndTime");
            return (Criteria) this;
        }

        public Criteria andTempEndTimeIn(List<Date> values) {
            addCriterion("temp_end_time in", values, "tempEndTime");
            return (Criteria) this;
        }

        public Criteria andTempEndTimeNotIn(List<Date> values) {
            addCriterion("temp_end_time not in", values, "tempEndTime");
            return (Criteria) this;
        }

        public Criteria andTempEndTimeBetween(Date value1, Date value2) {
            addCriterion("temp_end_time between", value1, value2, "tempEndTime");
            return (Criteria) this;
        }

        public Criteria andTempEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("temp_end_time not between", value1, value2, "tempEndTime");
            return (Criteria) this;
        }

        public Criteria andCreditModulusIsNull() {
            addCriterion("credit_modulus is null");
            return (Criteria) this;
        }

        public Criteria andCreditModulusIsNotNull() {
            addCriterion("credit_modulus is not null");
            return (Criteria) this;
        }

        public Criteria andCreditModulusEqualTo(Integer value) {
            addCriterion("credit_modulus =", value, "creditModulus");
            return (Criteria) this;
        }

        public Criteria andCreditModulusNotEqualTo(Integer value) {
            addCriterion("credit_modulus <>", value, "creditModulus");
            return (Criteria) this;
        }

        public Criteria andCreditModulusGreaterThan(Integer value) {
            addCriterion("credit_modulus >", value, "creditModulus");
            return (Criteria) this;
        }

        public Criteria andCreditModulusGreaterThanOrEqualTo(Integer value) {
            addCriterion("credit_modulus >=", value, "creditModulus");
            return (Criteria) this;
        }

        public Criteria andCreditModulusLessThan(Integer value) {
            addCriterion("credit_modulus <", value, "creditModulus");
            return (Criteria) this;
        }

        public Criteria andCreditModulusLessThanOrEqualTo(Integer value) {
            addCriterion("credit_modulus <=", value, "creditModulus");
            return (Criteria) this;
        }

        public Criteria andCreditModulusIn(List<Integer> values) {
            addCriterion("credit_modulus in", values, "creditModulus");
            return (Criteria) this;
        }

        public Criteria andCreditModulusNotIn(List<Integer> values) {
            addCriterion("credit_modulus not in", values, "creditModulus");
            return (Criteria) this;
        }

        public Criteria andCreditModulusBetween(Integer value1, Integer value2) {
            addCriterion("credit_modulus between", value1, value2, "creditModulus");
            return (Criteria) this;
        }

        public Criteria andCreditModulusNotBetween(Integer value1, Integer value2) {
            addCriterion("credit_modulus not between", value1, value2, "creditModulus");
            return (Criteria) this;
        }

        public Criteria andCustomerStatusIsNull() {
            addCriterion("customer_status is null");
            return (Criteria) this;
        }

        public Criteria andCustomerStatusIsNotNull() {
            addCriterion("customer_status is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerStatusEqualTo(String value) {
            addCriterion("customer_status =", value, "customerStatus");
            return (Criteria) this;
        }

        public Criteria andCustomerStatusNotEqualTo(String value) {
            addCriterion("customer_status <>", value, "customerStatus");
            return (Criteria) this;
        }

        public Criteria andCustomerStatusGreaterThan(String value) {
            addCriterion("customer_status >", value, "customerStatus");
            return (Criteria) this;
        }

        public Criteria andCustomerStatusGreaterThanOrEqualTo(String value) {
            addCriterion("customer_status >=", value, "customerStatus");
            return (Criteria) this;
        }

        public Criteria andCustomerStatusLessThan(String value) {
            addCriterion("customer_status <", value, "customerStatus");
            return (Criteria) this;
        }

        public Criteria andCustomerStatusLessThanOrEqualTo(String value) {
            addCriterion("customer_status <=", value, "customerStatus");
            return (Criteria) this;
        }

        public Criteria andCustomerStatusLike(String value) {
            addCriterion("customer_status like", value, "customerStatus");
            return (Criteria) this;
        }

        public Criteria andCustomerStatusNotLike(String value) {
            addCriterion("customer_status not like", value, "customerStatus");
            return (Criteria) this;
        }

        public Criteria andCustomerStatusIn(List<String> values) {
            addCriterion("customer_status in", values, "customerStatus");
            return (Criteria) this;
        }

        public Criteria andCustomerStatusNotIn(List<String> values) {
            addCriterion("customer_status not in", values, "customerStatus");
            return (Criteria) this;
        }

        public Criteria andCustomerStatusBetween(String value1, String value2) {
            addCriterion("customer_status between", value1, value2, "customerStatus");
            return (Criteria) this;
        }

        public Criteria andCustomerStatusNotBetween(String value1, String value2) {
            addCriterion("customer_status not between", value1, value2, "customerStatus");
            return (Criteria) this;
        }

        public Criteria andActiveFlagIsNull() {
            addCriterion("active_flag is null");
            return (Criteria) this;
        }

        public Criteria andActiveFlagIsNotNull() {
            addCriterion("active_flag is not null");
            return (Criteria) this;
        }

        public Criteria andActiveFlagEqualTo(Boolean value) {
            addCriterion("active_flag =", value, "activeFlag");
            return (Criteria) this;
        }

        public Criteria andActiveFlagNotEqualTo(Boolean value) {
            addCriterion("active_flag <>", value, "activeFlag");
            return (Criteria) this;
        }

        public Criteria andActiveFlagGreaterThan(Boolean value) {
            addCriterion("active_flag >", value, "activeFlag");
            return (Criteria) this;
        }

        public Criteria andActiveFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("active_flag >=", value, "activeFlag");
            return (Criteria) this;
        }

        public Criteria andActiveFlagLessThan(Boolean value) {
            addCriterion("active_flag <", value, "activeFlag");
            return (Criteria) this;
        }

        public Criteria andActiveFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("active_flag <=", value, "activeFlag");
            return (Criteria) this;
        }

        public Criteria andActiveFlagIn(List<Boolean> values) {
            addCriterion("active_flag in", values, "activeFlag");
            return (Criteria) this;
        }

        public Criteria andActiveFlagNotIn(List<Boolean> values) {
            addCriterion("active_flag not in", values, "activeFlag");
            return (Criteria) this;
        }

        public Criteria andActiveFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("active_flag between", value1, value2, "activeFlag");
            return (Criteria) this;
        }

        public Criteria andActiveFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("active_flag not between", value1, value2, "activeFlag");
            return (Criteria) this;
        }

        public Criteria andSyncFlagIsNull() {
            addCriterion("sync_flag is null");
            return (Criteria) this;
        }

        public Criteria andSyncFlagIsNotNull() {
            addCriterion("sync_flag is not null");
            return (Criteria) this;
        }

        public Criteria andSyncFlagEqualTo(Boolean value) {
            addCriterion("sync_flag =", value, "syncFlag");
            return (Criteria) this;
        }

        public Criteria andSyncFlagNotEqualTo(Boolean value) {
            addCriterion("sync_flag <>", value, "syncFlag");
            return (Criteria) this;
        }

        public Criteria andSyncFlagGreaterThan(Boolean value) {
            addCriterion("sync_flag >", value, "syncFlag");
            return (Criteria) this;
        }

        public Criteria andSyncFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("sync_flag >=", value, "syncFlag");
            return (Criteria) this;
        }

        public Criteria andSyncFlagLessThan(Boolean value) {
            addCriterion("sync_flag <", value, "syncFlag");
            return (Criteria) this;
        }

        public Criteria andSyncFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("sync_flag <=", value, "syncFlag");
            return (Criteria) this;
        }

        public Criteria andSyncFlagIn(List<Boolean> values) {
            addCriterion("sync_flag in", values, "syncFlag");
            return (Criteria) this;
        }

        public Criteria andSyncFlagNotIn(List<Boolean> values) {
            addCriterion("sync_flag not in", values, "syncFlag");
            return (Criteria) this;
        }

        public Criteria andSyncFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("sync_flag between", value1, value2, "syncFlag");
            return (Criteria) this;
        }

        public Criteria andSyncFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("sync_flag not between", value1, value2, "syncFlag");
            return (Criteria) this;
        }

        public Criteria andChannelFirstCodeIsNull() {
            addCriterion("channel_first_code is null");
            return (Criteria) this;
        }

        public Criteria andChannelFirstCodeIsNotNull() {
            addCriterion("channel_first_code is not null");
            return (Criteria) this;
        }

        public Criteria andChannelFirstCodeEqualTo(String value) {
            addCriterion("channel_first_code =", value, "channelFirstCode");
            return (Criteria) this;
        }

        public Criteria andChannelFirstCodeNotEqualTo(String value) {
            addCriterion("channel_first_code <>", value, "channelFirstCode");
            return (Criteria) this;
        }

        public Criteria andChannelFirstCodeGreaterThan(String value) {
            addCriterion("channel_first_code >", value, "channelFirstCode");
            return (Criteria) this;
        }

        public Criteria andChannelFirstCodeGreaterThanOrEqualTo(String value) {
            addCriterion("channel_first_code >=", value, "channelFirstCode");
            return (Criteria) this;
        }

        public Criteria andChannelFirstCodeLessThan(String value) {
            addCriterion("channel_first_code <", value, "channelFirstCode");
            return (Criteria) this;
        }

        public Criteria andChannelFirstCodeLessThanOrEqualTo(String value) {
            addCriterion("channel_first_code <=", value, "channelFirstCode");
            return (Criteria) this;
        }

        public Criteria andChannelFirstCodeLike(String value) {
            addCriterion("channel_first_code like", value, "channelFirstCode");
            return (Criteria) this;
        }

        public Criteria andChannelFirstCodeNotLike(String value) {
            addCriterion("channel_first_code not like", value, "channelFirstCode");
            return (Criteria) this;
        }

        public Criteria andChannelFirstCodeIn(List<String> values) {
            addCriterion("channel_first_code in", values, "channelFirstCode");
            return (Criteria) this;
        }

        public Criteria andChannelFirstCodeNotIn(List<String> values) {
            addCriterion("channel_first_code not in", values, "channelFirstCode");
            return (Criteria) this;
        }

        public Criteria andChannelFirstCodeBetween(String value1, String value2) {
            addCriterion("channel_first_code between", value1, value2, "channelFirstCode");
            return (Criteria) this;
        }

        public Criteria andChannelFirstCodeNotBetween(String value1, String value2) {
            addCriterion("channel_first_code not between", value1, value2, "channelFirstCode");
            return (Criteria) this;
        }

        public Criteria andChannelSecondCodeIsNull() {
            addCriterion("channel_second_code is null");
            return (Criteria) this;
        }

        public Criteria andChannelSecondCodeIsNotNull() {
            addCriterion("channel_second_code is not null");
            return (Criteria) this;
        }

        public Criteria andChannelSecondCodeEqualTo(String value) {
            addCriterion("channel_second_code =", value, "channelSecondCode");
            return (Criteria) this;
        }

        public Criteria andChannelSecondCodeNotEqualTo(String value) {
            addCriterion("channel_second_code <>", value, "channelSecondCode");
            return (Criteria) this;
        }

        public Criteria andChannelSecondCodeGreaterThan(String value) {
            addCriterion("channel_second_code >", value, "channelSecondCode");
            return (Criteria) this;
        }

        public Criteria andChannelSecondCodeGreaterThanOrEqualTo(String value) {
            addCriterion("channel_second_code >=", value, "channelSecondCode");
            return (Criteria) this;
        }

        public Criteria andChannelSecondCodeLessThan(String value) {
            addCriterion("channel_second_code <", value, "channelSecondCode");
            return (Criteria) this;
        }

        public Criteria andChannelSecondCodeLessThanOrEqualTo(String value) {
            addCriterion("channel_second_code <=", value, "channelSecondCode");
            return (Criteria) this;
        }

        public Criteria andChannelSecondCodeLike(String value) {
            addCriterion("channel_second_code like", value, "channelSecondCode");
            return (Criteria) this;
        }

        public Criteria andChannelSecondCodeNotLike(String value) {
            addCriterion("channel_second_code not like", value, "channelSecondCode");
            return (Criteria) this;
        }

        public Criteria andChannelSecondCodeIn(List<String> values) {
            addCriterion("channel_second_code in", values, "channelSecondCode");
            return (Criteria) this;
        }

        public Criteria andChannelSecondCodeNotIn(List<String> values) {
            addCriterion("channel_second_code not in", values, "channelSecondCode");
            return (Criteria) this;
        }

        public Criteria andChannelSecondCodeBetween(String value1, String value2) {
            addCriterion("channel_second_code between", value1, value2, "channelSecondCode");
            return (Criteria) this;
        }

        public Criteria andChannelSecondCodeNotBetween(String value1, String value2) {
            addCriterion("channel_second_code not between", value1, value2, "channelSecondCode");
            return (Criteria) this;
        }

        public Criteria andSaleOrganizationIsNull() {
            addCriterion("sale_organization is null");
            return (Criteria) this;
        }

        public Criteria andSaleOrganizationIsNotNull() {
            addCriterion("sale_organization is not null");
            return (Criteria) this;
        }

        public Criteria andSaleOrganizationEqualTo(String value) {
            addCriterion("sale_organization =", value, "saleOrganization");
            return (Criteria) this;
        }

        public Criteria andSaleOrganizationNotEqualTo(String value) {
            addCriterion("sale_organization <>", value, "saleOrganization");
            return (Criteria) this;
        }

        public Criteria andSaleOrganizationGreaterThan(String value) {
            addCriterion("sale_organization >", value, "saleOrganization");
            return (Criteria) this;
        }

        public Criteria andSaleOrganizationGreaterThanOrEqualTo(String value) {
            addCriterion("sale_organization >=", value, "saleOrganization");
            return (Criteria) this;
        }

        public Criteria andSaleOrganizationLessThan(String value) {
            addCriterion("sale_organization <", value, "saleOrganization");
            return (Criteria) this;
        }

        public Criteria andSaleOrganizationLessThanOrEqualTo(String value) {
            addCriterion("sale_organization <=", value, "saleOrganization");
            return (Criteria) this;
        }

        public Criteria andSaleOrganizationLike(String value) {
            addCriterion("sale_organization like", value, "saleOrganization");
            return (Criteria) this;
        }

        public Criteria andSaleOrganizationNotLike(String value) {
            addCriterion("sale_organization not like", value, "saleOrganization");
            return (Criteria) this;
        }

        public Criteria andSaleOrganizationIn(List<String> values) {
            addCriterion("sale_organization in", values, "saleOrganization");
            return (Criteria) this;
        }

        public Criteria andSaleOrganizationNotIn(List<String> values) {
            addCriterion("sale_organization not in", values, "saleOrganization");
            return (Criteria) this;
        }

        public Criteria andSaleOrganizationBetween(String value1, String value2) {
            addCriterion("sale_organization between", value1, value2, "saleOrganization");
            return (Criteria) this;
        }

        public Criteria andSaleOrganizationNotBetween(String value1, String value2) {
            addCriterion("sale_organization not between", value1, value2, "saleOrganization");
            return (Criteria) this;
        }

        public Criteria andDistributionChannelIsNull() {
            addCriterion("distribution_channel is null");
            return (Criteria) this;
        }

        public Criteria andDistributionChannelIsNotNull() {
            addCriterion("distribution_channel is not null");
            return (Criteria) this;
        }

        public Criteria andDistributionChannelEqualTo(String value) {
            addCriterion("distribution_channel =", value, "distributionChannel");
            return (Criteria) this;
        }

        public Criteria andDistributionChannelNotEqualTo(String value) {
            addCriterion("distribution_channel <>", value, "distributionChannel");
            return (Criteria) this;
        }

        public Criteria andDistributionChannelGreaterThan(String value) {
            addCriterion("distribution_channel >", value, "distributionChannel");
            return (Criteria) this;
        }

        public Criteria andDistributionChannelGreaterThanOrEqualTo(String value) {
            addCriterion("distribution_channel >=", value, "distributionChannel");
            return (Criteria) this;
        }

        public Criteria andDistributionChannelLessThan(String value) {
            addCriterion("distribution_channel <", value, "distributionChannel");
            return (Criteria) this;
        }

        public Criteria andDistributionChannelLessThanOrEqualTo(String value) {
            addCriterion("distribution_channel <=", value, "distributionChannel");
            return (Criteria) this;
        }

        public Criteria andDistributionChannelLike(String value) {
            addCriterion("distribution_channel like", value, "distributionChannel");
            return (Criteria) this;
        }

        public Criteria andDistributionChannelNotLike(String value) {
            addCriterion("distribution_channel not like", value, "distributionChannel");
            return (Criteria) this;
        }

        public Criteria andDistributionChannelIn(List<String> values) {
            addCriterion("distribution_channel in", values, "distributionChannel");
            return (Criteria) this;
        }

        public Criteria andDistributionChannelNotIn(List<String> values) {
            addCriterion("distribution_channel not in", values, "distributionChannel");
            return (Criteria) this;
        }

        public Criteria andDistributionChannelBetween(String value1, String value2) {
            addCriterion("distribution_channel between", value1, value2, "distributionChannel");
            return (Criteria) this;
        }

        public Criteria andDistributionChannelNotBetween(String value1, String value2) {
            addCriterion("distribution_channel not between", value1, value2, "distributionChannel");
            return (Criteria) this;
        }

        public Criteria andSaleRegionIsNull() {
            addCriterion("sale_region is null");
            return (Criteria) this;
        }

        public Criteria andSaleRegionIsNotNull() {
            addCriterion("sale_region is not null");
            return (Criteria) this;
        }

        public Criteria andSaleRegionEqualTo(String value) {
            addCriterion("sale_region =", value, "saleRegion");
            return (Criteria) this;
        }

        public Criteria andSaleRegionNotEqualTo(String value) {
            addCriterion("sale_region <>", value, "saleRegion");
            return (Criteria) this;
        }

        public Criteria andSaleRegionGreaterThan(String value) {
            addCriterion("sale_region >", value, "saleRegion");
            return (Criteria) this;
        }

        public Criteria andSaleRegionGreaterThanOrEqualTo(String value) {
            addCriterion("sale_region >=", value, "saleRegion");
            return (Criteria) this;
        }

        public Criteria andSaleRegionLessThan(String value) {
            addCriterion("sale_region <", value, "saleRegion");
            return (Criteria) this;
        }

        public Criteria andSaleRegionLessThanOrEqualTo(String value) {
            addCriterion("sale_region <=", value, "saleRegion");
            return (Criteria) this;
        }

        public Criteria andSaleRegionLike(String value) {
            addCriterion("sale_region like", value, "saleRegion");
            return (Criteria) this;
        }

        public Criteria andSaleRegionNotLike(String value) {
            addCriterion("sale_region not like", value, "saleRegion");
            return (Criteria) this;
        }

        public Criteria andSaleRegionIn(List<String> values) {
            addCriterion("sale_region in", values, "saleRegion");
            return (Criteria) this;
        }

        public Criteria andSaleRegionNotIn(List<String> values) {
            addCriterion("sale_region not in", values, "saleRegion");
            return (Criteria) this;
        }

        public Criteria andSaleRegionBetween(String value1, String value2) {
            addCriterion("sale_region between", value1, value2, "saleRegion");
            return (Criteria) this;
        }

        public Criteria andSaleRegionNotBetween(String value1, String value2) {
            addCriterion("sale_region not between", value1, value2, "saleRegion");
            return (Criteria) this;
        }

        public Criteria andCreditRangeIsNull() {
            addCriterion("credit_range is null");
            return (Criteria) this;
        }

        public Criteria andCreditRangeIsNotNull() {
            addCriterion("credit_range is not null");
            return (Criteria) this;
        }

        public Criteria andCreditRangeEqualTo(String value) {
            addCriterion("credit_range =", value, "creditRange");
            return (Criteria) this;
        }

        public Criteria andCreditRangeNotEqualTo(String value) {
            addCriterion("credit_range <>", value, "creditRange");
            return (Criteria) this;
        }

        public Criteria andCreditRangeGreaterThan(String value) {
            addCriterion("credit_range >", value, "creditRange");
            return (Criteria) this;
        }

        public Criteria andCreditRangeGreaterThanOrEqualTo(String value) {
            addCriterion("credit_range >=", value, "creditRange");
            return (Criteria) this;
        }

        public Criteria andCreditRangeLessThan(String value) {
            addCriterion("credit_range <", value, "creditRange");
            return (Criteria) this;
        }

        public Criteria andCreditRangeLessThanOrEqualTo(String value) {
            addCriterion("credit_range <=", value, "creditRange");
            return (Criteria) this;
        }

        public Criteria andCreditRangeLike(String value) {
            addCriterion("credit_range like", value, "creditRange");
            return (Criteria) this;
        }

        public Criteria andCreditRangeNotLike(String value) {
            addCriterion("credit_range not like", value, "creditRange");
            return (Criteria) this;
        }

        public Criteria andCreditRangeIn(List<String> values) {
            addCriterion("credit_range in", values, "creditRange");
            return (Criteria) this;
        }

        public Criteria andCreditRangeNotIn(List<String> values) {
            addCriterion("credit_range not in", values, "creditRange");
            return (Criteria) this;
        }

        public Criteria andCreditRangeBetween(String value1, String value2) {
            addCriterion("credit_range between", value1, value2, "creditRange");
            return (Criteria) this;
        }

        public Criteria andCreditRangeNotBetween(String value1, String value2) {
            addCriterion("credit_range not between", value1, value2, "creditRange");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andContractBeginTimeIsNull() {
            addCriterion("contract_begin_time is null");
            return (Criteria) this;
        }

        public Criteria andContractBeginTimeIsNotNull() {
            addCriterion("contract_begin_time is not null");
            return (Criteria) this;
        }

        public Criteria andContractBeginTimeEqualTo(Date value) {
            addCriterion("contract_begin_time =", value, "contractBeginTime");
            return (Criteria) this;
        }

        public Criteria andContractBeginTimeNotEqualTo(Date value) {
            addCriterion("contract_begin_time <>", value, "contractBeginTime");
            return (Criteria) this;
        }

        public Criteria andContractBeginTimeGreaterThan(Date value) {
            addCriterion("contract_begin_time >", value, "contractBeginTime");
            return (Criteria) this;
        }

        public Criteria andContractBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("contract_begin_time >=", value, "contractBeginTime");
            return (Criteria) this;
        }

        public Criteria andContractBeginTimeLessThan(Date value) {
            addCriterion("contract_begin_time <", value, "contractBeginTime");
            return (Criteria) this;
        }

        public Criteria andContractBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("contract_begin_time <=", value, "contractBeginTime");
            return (Criteria) this;
        }

        public Criteria andContractBeginTimeIn(List<Date> values) {
            addCriterion("contract_begin_time in", values, "contractBeginTime");
            return (Criteria) this;
        }

        public Criteria andContractBeginTimeNotIn(List<Date> values) {
            addCriterion("contract_begin_time not in", values, "contractBeginTime");
            return (Criteria) this;
        }

        public Criteria andContractBeginTimeBetween(Date value1, Date value2) {
            addCriterion("contract_begin_time between", value1, value2, "contractBeginTime");
            return (Criteria) this;
        }

        public Criteria andContractBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("contract_begin_time not between", value1, value2, "contractBeginTime");
            return (Criteria) this;
        }

        public Criteria andContractEndTimeIsNull() {
            addCriterion("contract_end_time is null");
            return (Criteria) this;
        }

        public Criteria andContractEndTimeIsNotNull() {
            addCriterion("contract_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andContractEndTimeEqualTo(Date value) {
            addCriterion("contract_end_time =", value, "contractEndTime");
            return (Criteria) this;
        }

        public Criteria andContractEndTimeNotEqualTo(Date value) {
            addCriterion("contract_end_time <>", value, "contractEndTime");
            return (Criteria) this;
        }

        public Criteria andContractEndTimeGreaterThan(Date value) {
            addCriterion("contract_end_time >", value, "contractEndTime");
            return (Criteria) this;
        }

        public Criteria andContractEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("contract_end_time >=", value, "contractEndTime");
            return (Criteria) this;
        }

        public Criteria andContractEndTimeLessThan(Date value) {
            addCriterion("contract_end_time <", value, "contractEndTime");
            return (Criteria) this;
        }

        public Criteria andContractEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("contract_end_time <=", value, "contractEndTime");
            return (Criteria) this;
        }

        public Criteria andContractEndTimeIn(List<Date> values) {
            addCriterion("contract_end_time in", values, "contractEndTime");
            return (Criteria) this;
        }

        public Criteria andContractEndTimeNotIn(List<Date> values) {
            addCriterion("contract_end_time not in", values, "contractEndTime");
            return (Criteria) this;
        }

        public Criteria andContractEndTimeBetween(Date value1, Date value2) {
            addCriterion("contract_end_time between", value1, value2, "contractEndTime");
            return (Criteria) this;
        }

        public Criteria andContractEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("contract_end_time not between", value1, value2, "contractEndTime");
            return (Criteria) this;
        }

        public Criteria andCustomerNatureIsNull() {
            addCriterion("customer_nature is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNatureIsNotNull() {
            addCriterion("customer_nature is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerNatureEqualTo(String value) {
            addCriterion("customer_nature =", value, "customerNature");
            return (Criteria) this;
        }

        public Criteria andCustomerNatureNotEqualTo(String value) {
            addCriterion("customer_nature <>", value, "customerNature");
            return (Criteria) this;
        }

        public Criteria andCustomerNatureGreaterThan(String value) {
            addCriterion("customer_nature >", value, "customerNature");
            return (Criteria) this;
        }

        public Criteria andCustomerNatureGreaterThanOrEqualTo(String value) {
            addCriterion("customer_nature >=", value, "customerNature");
            return (Criteria) this;
        }

        public Criteria andCustomerNatureLessThan(String value) {
            addCriterion("customer_nature <", value, "customerNature");
            return (Criteria) this;
        }

        public Criteria andCustomerNatureLessThanOrEqualTo(String value) {
            addCriterion("customer_nature <=", value, "customerNature");
            return (Criteria) this;
        }

        public Criteria andCustomerNatureLike(String value) {
            addCriterion("customer_nature like", value, "customerNature");
            return (Criteria) this;
        }

        public Criteria andCustomerNatureNotLike(String value) {
            addCriterion("customer_nature not like", value, "customerNature");
            return (Criteria) this;
        }

        public Criteria andCustomerNatureIn(List<String> values) {
            addCriterion("customer_nature in", values, "customerNature");
            return (Criteria) this;
        }

        public Criteria andCustomerNatureNotIn(List<String> values) {
            addCriterion("customer_nature not in", values, "customerNature");
            return (Criteria) this;
        }

        public Criteria andCustomerNatureBetween(String value1, String value2) {
            addCriterion("customer_nature between", value1, value2, "customerNature");
            return (Criteria) this;
        }

        public Criteria andCustomerNatureNotBetween(String value1, String value2) {
            addCriterion("customer_nature not between", value1, value2, "customerNature");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressIsNull() {
            addCriterion("invoice_address is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressIsNotNull() {
            addCriterion("invoice_address is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressEqualTo(String value) {
            addCriterion("invoice_address =", value, "invoiceAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressNotEqualTo(String value) {
            addCriterion("invoice_address <>", value, "invoiceAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressGreaterThan(String value) {
            addCriterion("invoice_address >", value, "invoiceAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_address >=", value, "invoiceAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressLessThan(String value) {
            addCriterion("invoice_address <", value, "invoiceAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressLessThanOrEqualTo(String value) {
            addCriterion("invoice_address <=", value, "invoiceAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressLike(String value) {
            addCriterion("invoice_address like", value, "invoiceAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressNotLike(String value) {
            addCriterion("invoice_address not like", value, "invoiceAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressIn(List<String> values) {
            addCriterion("invoice_address in", values, "invoiceAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressNotIn(List<String> values) {
            addCriterion("invoice_address not in", values, "invoiceAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressBetween(String value1, String value2) {
            addCriterion("invoice_address between", value1, value2, "invoiceAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressNotBetween(String value1, String value2) {
            addCriterion("invoice_address not between", value1, value2, "invoiceAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressDetailsIsNull() {
            addCriterion("invoice_address_details is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressDetailsIsNotNull() {
            addCriterion("invoice_address_details is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressDetailsEqualTo(String value) {
            addCriterion("invoice_address_details =", value, "invoiceAddressDetails");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressDetailsNotEqualTo(String value) {
            addCriterion("invoice_address_details <>", value, "invoiceAddressDetails");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressDetailsGreaterThan(String value) {
            addCriterion("invoice_address_details >", value, "invoiceAddressDetails");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressDetailsGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_address_details >=", value, "invoiceAddressDetails");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressDetailsLessThan(String value) {
            addCriterion("invoice_address_details <", value, "invoiceAddressDetails");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressDetailsLessThanOrEqualTo(String value) {
            addCriterion("invoice_address_details <=", value, "invoiceAddressDetails");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressDetailsLike(String value) {
            addCriterion("invoice_address_details like", value, "invoiceAddressDetails");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressDetailsNotLike(String value) {
            addCriterion("invoice_address_details not like", value, "invoiceAddressDetails");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressDetailsIn(List<String> values) {
            addCriterion("invoice_address_details in", values, "invoiceAddressDetails");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressDetailsNotIn(List<String> values) {
            addCriterion("invoice_address_details not in", values, "invoiceAddressDetails");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressDetailsBetween(String value1, String value2) {
            addCriterion("invoice_address_details between", value1, value2, "invoiceAddressDetails");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressDetailsNotBetween(String value1, String value2) {
            addCriterion("invoice_address_details not between", value1, value2, "invoiceAddressDetails");
            return (Criteria) this;
        }

        public Criteria andHaveThirdPartyCreditFileIsNull() {
            addCriterion("have_third_party_credit_file is null");
            return (Criteria) this;
        }

        public Criteria andHaveThirdPartyCreditFileIsNotNull() {
            addCriterion("have_third_party_credit_file is not null");
            return (Criteria) this;
        }

        public Criteria andHaveThirdPartyCreditFileEqualTo(Boolean value) {
            addCriterion("have_third_party_credit_file =", value, "haveThirdPartyCreditFile");
            return (Criteria) this;
        }

        public Criteria andHaveThirdPartyCreditFileNotEqualTo(Boolean value) {
            addCriterion("have_third_party_credit_file <>", value, "haveThirdPartyCreditFile");
            return (Criteria) this;
        }

        public Criteria andHaveThirdPartyCreditFileGreaterThan(Boolean value) {
            addCriterion("have_third_party_credit_file >", value, "haveThirdPartyCreditFile");
            return (Criteria) this;
        }

        public Criteria andHaveThirdPartyCreditFileGreaterThanOrEqualTo(Boolean value) {
            addCriterion("have_third_party_credit_file >=", value, "haveThirdPartyCreditFile");
            return (Criteria) this;
        }

        public Criteria andHaveThirdPartyCreditFileLessThan(Boolean value) {
            addCriterion("have_third_party_credit_file <", value, "haveThirdPartyCreditFile");
            return (Criteria) this;
        }

        public Criteria andHaveThirdPartyCreditFileLessThanOrEqualTo(Boolean value) {
            addCriterion("have_third_party_credit_file <=", value, "haveThirdPartyCreditFile");
            return (Criteria) this;
        }

        public Criteria andHaveThirdPartyCreditFileIn(List<Boolean> values) {
            addCriterion("have_third_party_credit_file in", values, "haveThirdPartyCreditFile");
            return (Criteria) this;
        }

        public Criteria andHaveThirdPartyCreditFileNotIn(List<Boolean> values) {
            addCriterion("have_third_party_credit_file not in", values, "haveThirdPartyCreditFile");
            return (Criteria) this;
        }

        public Criteria andHaveThirdPartyCreditFileBetween(Boolean value1, Boolean value2) {
            addCriterion("have_third_party_credit_file between", value1, value2, "haveThirdPartyCreditFile");
            return (Criteria) this;
        }

        public Criteria andHaveThirdPartyCreditFileNotBetween(Boolean value1, Boolean value2) {
            addCriterion("have_third_party_credit_file not between", value1, value2, "haveThirdPartyCreditFile");
            return (Criteria) this;
        }

        public Criteria andCustomerNatureIdIsNull() {
            addCriterion("customer_nature_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNatureIdIsNotNull() {
            addCriterion("customer_nature_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerNatureIdEqualTo(Integer value) {
            addCriterion("customer_nature_id =", value, "customerNatureId");
            return (Criteria) this;
        }

        public Criteria andCustomerNatureIdNotEqualTo(Integer value) {
            addCriterion("customer_nature_id <>", value, "customerNatureId");
            return (Criteria) this;
        }

        public Criteria andCustomerNatureIdGreaterThan(Integer value) {
            addCriterion("customer_nature_id >", value, "customerNatureId");
            return (Criteria) this;
        }

        public Criteria andCustomerNatureIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("customer_nature_id >=", value, "customerNatureId");
            return (Criteria) this;
        }

        public Criteria andCustomerNatureIdLessThan(Integer value) {
            addCriterion("customer_nature_id <", value, "customerNatureId");
            return (Criteria) this;
        }

        public Criteria andCustomerNatureIdLessThanOrEqualTo(Integer value) {
            addCriterion("customer_nature_id <=", value, "customerNatureId");
            return (Criteria) this;
        }

        public Criteria andCustomerNatureIdIn(List<Integer> values) {
            addCriterion("customer_nature_id in", values, "customerNatureId");
            return (Criteria) this;
        }

        public Criteria andCustomerNatureIdNotIn(List<Integer> values) {
            addCriterion("customer_nature_id not in", values, "customerNatureId");
            return (Criteria) this;
        }

        public Criteria andCustomerNatureIdBetween(Integer value1, Integer value2) {
            addCriterion("customer_nature_id between", value1, value2, "customerNatureId");
            return (Criteria) this;
        }

        public Criteria andCustomerNatureIdNotBetween(Integer value1, Integer value2) {
            addCriterion("customer_nature_id not between", value1, value2, "customerNatureId");
            return (Criteria) this;
        }

        public Criteria andArchiveCategoryIdIsNull() {
            addCriterion("archive_category_id is null");
            return (Criteria) this;
        }

        public Criteria andArchiveCategoryIdIsNotNull() {
            addCriterion("archive_category_id is not null");
            return (Criteria) this;
        }

        public Criteria andArchiveCategoryIdEqualTo(Integer value) {
            addCriterion("archive_category_id =", value, "archiveCategoryId");
            return (Criteria) this;
        }

        public Criteria andArchiveCategoryIdNotEqualTo(Integer value) {
            addCriterion("archive_category_id <>", value, "archiveCategoryId");
            return (Criteria) this;
        }

        public Criteria andArchiveCategoryIdGreaterThan(Integer value) {
            addCriterion("archive_category_id >", value, "archiveCategoryId");
            return (Criteria) this;
        }

        public Criteria andArchiveCategoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("archive_category_id >=", value, "archiveCategoryId");
            return (Criteria) this;
        }

        public Criteria andArchiveCategoryIdLessThan(Integer value) {
            addCriterion("archive_category_id <", value, "archiveCategoryId");
            return (Criteria) this;
        }

        public Criteria andArchiveCategoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("archive_category_id <=", value, "archiveCategoryId");
            return (Criteria) this;
        }

        public Criteria andArchiveCategoryIdIn(List<Integer> values) {
            addCriterion("archive_category_id in", values, "archiveCategoryId");
            return (Criteria) this;
        }

        public Criteria andArchiveCategoryIdNotIn(List<Integer> values) {
            addCriterion("archive_category_id not in", values, "archiveCategoryId");
            return (Criteria) this;
        }

        public Criteria andArchiveCategoryIdBetween(Integer value1, Integer value2) {
            addCriterion("archive_category_id between", value1, value2, "archiveCategoryId");
            return (Criteria) this;
        }

        public Criteria andArchiveCategoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("archive_category_id not between", value1, value2, "archiveCategoryId");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalIdIsNull() {
            addCriterion("registered_capital_id is null");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalIdIsNotNull() {
            addCriterion("registered_capital_id is not null");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalIdEqualTo(Integer value) {
            addCriterion("registered_capital_id =", value, "registeredCapitalId");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalIdNotEqualTo(Integer value) {
            addCriterion("registered_capital_id <>", value, "registeredCapitalId");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalIdGreaterThan(Integer value) {
            addCriterion("registered_capital_id >", value, "registeredCapitalId");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("registered_capital_id >=", value, "registeredCapitalId");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalIdLessThan(Integer value) {
            addCriterion("registered_capital_id <", value, "registeredCapitalId");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalIdLessThanOrEqualTo(Integer value) {
            addCriterion("registered_capital_id <=", value, "registeredCapitalId");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalIdIn(List<Integer> values) {
            addCriterion("registered_capital_id in", values, "registeredCapitalId");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalIdNotIn(List<Integer> values) {
            addCriterion("registered_capital_id not in", values, "registeredCapitalId");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalIdBetween(Integer value1, Integer value2) {
            addCriterion("registered_capital_id between", value1, value2, "registeredCapitalId");
            return (Criteria) this;
        }

        public Criteria andRegisteredCapitalIdNotBetween(Integer value1, Integer value2) {
            addCriterion("registered_capital_id not between", value1, value2, "registeredCapitalId");
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

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("create_by like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("create_by not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
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

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("update_by like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("update_by not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
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