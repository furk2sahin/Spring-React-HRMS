import { instance } from "./instance/Instance"

export const createEmployer = (employer) => {
    return instance.post("/employer/add", employer)
}

export const getEmployers = () => {
    return instance.get("/employer/get-all")
}

export const getEmployersPaged = (pageNumber, pageSize) => {
    return instance.get(`/employer/getAllPaged/${pageNumber}/${pageSize}`)
}