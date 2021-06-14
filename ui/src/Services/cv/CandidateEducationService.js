import { instance } from "../instance/Instance"

export const createCandidateEducation = (education) => {
    return instance.post("/candidate-education/add", education)
}

export const findAllEducationByCandidateCVIdOrderByEndDateDesc = (id) => {
    return instance.get(`/candidate-education/order-by-end-date-desc/${id}`)
}