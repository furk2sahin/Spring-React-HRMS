import { instance } from "../instance/Instance"

export const createCandidateTechnology = (technology) => {
    return instance.post("/candidate-technologies/add", technology)
}

export const getCandidateTechnologies = () => {
    return instance.get("/candidate-technologies/get-all")
}