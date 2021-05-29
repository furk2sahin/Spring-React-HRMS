package kodlamaio.hrms.core.enums;

import org.springframework.data.domain.Sort;

public enum JobAdvertiseSort {
    BY_CREATE_DATE_DESC(Sort.Direction.DESC, "createDate"),
    BY_CREATE_DATE_ASC(Sort.Direction.ASC, "createDate"),
    BY_MAX_SALARY_DESC(Sort.Direction.DESC, "maxSalary"),
    BY_MAX_SALARY_ASC(Sort.Direction.ASC, "maxSalary"),
    BY_MIN_SALARY_DESC(Sort.Direction.DESC, "minSalary"),
    BY_MIN_SALARY_ASC(Sort.Direction.ASC, "minSalary"),
    BY_EXPIRATION_DATE_DESC(Sort.Direction.DESC, "expirationDate"),
    BY_EXPIRATION_DATE_ASC(Sort.Direction.ASC, "expirationDate");

    private Sort.Direction direction;
    private String property;

    public Sort.Direction getDirection() {
        return direction;
    }

    public String getProperty() {
        return property;
    }

    JobAdvertiseSort(Sort.Direction direction, String property) {
        this.direction = direction;
        this.property = property;
    }
}
