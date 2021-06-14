import { instance } from "../Instance";

export const getCities = () => {
    return instance.get("/cities/get-all")
}

export const findCityById = (id) => {
    return instance.get(`/cities/find-by-id/${id}`)
}