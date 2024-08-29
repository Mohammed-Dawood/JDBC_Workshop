package se.lexicon.model;

public class City {
    private int cityId;
    private String cityName;
    private String cityCode;
    private String cityDistrict;
    private int cityPopulation;

    public City(String cityName, String cityCode, String cityDistrict, int cityPopulation) {
        this.cityName = cityName;
        this.cityCode = cityCode;
        this.cityDistrict = cityDistrict;
        this.cityPopulation = cityPopulation;
    }

    public City(int cityId, String cityName, String cityCode, String cityDistrict, int cityPopulation) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.cityCode = cityCode;
        this.cityDistrict = cityDistrict;
        this.cityPopulation = cityPopulation;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityDistrict() {
        return cityDistrict;
    }

    public void setCityDistrict(String cityDistrict) {
        this.cityDistrict = cityDistrict;
    }

    public int getCityPopulation() {
        return cityPopulation;
    }

    public void setCityPopulation(int cityPopulation) {
        this.cityPopulation = cityPopulation;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", cityDistrict='" + cityDistrict + '\'' +
                ", cityPopulation=" + cityPopulation +
                '}';
    }
}
