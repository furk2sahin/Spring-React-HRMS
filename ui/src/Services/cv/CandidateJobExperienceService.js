
import { instance } from '../instance/Instance'

export const createCandidateJobExperience = (jobExperience) => {
    return instance.post("/job-experiences/add", jobExperience)
}

export const getCandidateJobExperiences = () => {
    return instance.get("/job-experiences/get-all")
}

export const findAllJobExperiencesByCandidateCVIdOrderByEndDateDesc = (cvId) => {
    instance.get(`/order-by-end-date-desc/${cvId}`)
}