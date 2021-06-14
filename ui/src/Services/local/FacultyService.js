import { instance } from "../Instance"

export const findByFacultyId = (id) => {
    return instance.get(`/faculty/find-by-id/${id}`)
}

export const findFacultyByUniversityId = (id) => {
    return instance.get(`/faculty/find-by-university-id/${id}`)
}