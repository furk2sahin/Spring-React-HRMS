import { instance } from "./instance/Instance"

export const createSystemPersonnel = (personnel) => {
    return instance.post("/system-personnel/add", personnel)
}

export const getSystemPersonnelPages = (pageNumber, pageSize) => {
    return instance.get(`/system-personnel/getAllPaged/${pageNumber}/${pageSize}`)
}