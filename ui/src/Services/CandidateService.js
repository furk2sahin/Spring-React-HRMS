import { instance } from "./instance/Instance"

export const createCandidate = (candidate) => {
    return instance.post("/candidate/add", candidate)
}

export const getCandidates = () => {
    return instance.get("/candidate/get-all")
}

export const getCandidatesPaged = (pageNumber, pageSize) => {
    return instance.get(`/candidate/getAllPaged/${pageNumber}/${pageSize}`)
}