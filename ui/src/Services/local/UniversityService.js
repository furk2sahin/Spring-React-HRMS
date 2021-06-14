import { instance } from "../Instance"

export const findUniversityById = (id) => {
    return instance.get(`/university/find-by-id/${id}`)
}

export const findUniversityByCityId = (id) => {
    return instance.get(`/university/find-by-city-id/${id}`)
}