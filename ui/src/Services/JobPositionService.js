import { instance } from "./instance/Instance"

export const getJobPositions = () => {
    return instance.get("/job-positions/get-all")
}

export const getJobPositionByName = (name) => {
    return instance.get(`/job-positions/get-by-job-name/${name}`)
}

export const createJobPosition = (jobPosition) => {
    return instance.post("/job-positions/add", jobPosition)
}

export const getJobPositionPaged = (pageNumber, pageSize) => {
    return instance.get(`/job-positions/getAllPaged/${pageNumber}/${pageSize}`)
}