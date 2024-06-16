package exercise.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductParamsDTO {
    private String titleCont;
    private Long categoryId;
    private Integer priceLt;
    private Integer priceGt;
    private Double ratingGt;

    public Long getCategoryId() {
        return categoryId;
    }

    public Double getRatingGt() {
        return ratingGt;
    }

    public Integer getPriceGt() {
        return priceGt;
    }

    public Integer getPriceLt() {
        return priceLt;
    }

    public String getTitleCont() {
        return titleCont;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setPriceGt(Integer priceGt) {
        this.priceGt = priceGt;
    }

    public void setPriceLt(Integer priceLt) {
        this.priceLt = priceLt;
    }

    public void setRatingGt(Double ratingGt) {
        this.ratingGt = ratingGt;
    }

    public void setTitleCont(String titleCont) {
        this.titleCont = titleCont;
    }
}
