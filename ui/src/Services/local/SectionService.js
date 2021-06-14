import { instance } from "../Instance"

export const findSectionById = (id) => {
    return instance.get(`/section/find-by-id/${id}`)
}

export const findSectionByFacultyId = (id) => {
    return instance.get(`/section/find-by-faculty-id/${id}`)
}