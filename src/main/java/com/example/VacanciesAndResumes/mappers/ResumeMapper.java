package com.example.VacanciesAndResumes.mappers;

import com.example.VacanciesAndResumes.DTOs.*;
import com.example.VacanciesAndResumes.models.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResumeMapper {
    ResumeDTO resumeToResumeDTO(Resume resume);
    Resume resumeDTOToResume(ResumeDTO resumeDTO);

    List<ResumeDTO> resumeToResumeDTO(List<Resume> resume);
    List<Resume> resumeDTOToResume(List<ResumeDTO> resumeDTO);

    AdditionalInfoDTO additionalInfoToAdditionalInfoDTO(AdditionalInfo additionalInfo);
    AdditionalInfo additionalInfoDTOToAdditionalInfo(AdditionalInfoDTO additionalInfoDTO);

    @Mapping(target="dateOfBirth", source="personalInfo.dateOfBirth", dateFormat = "yyyy.MM.dd")
    PersonalInfoDTO personalInfoToPersonalInfoDTO(PersonalInfo personalInfo);
    @Mapping(target="dateOfBirth", source="personalInfoDTO.dateOfBirth", dateFormat = "yyyy.MM.dd")
    PersonalInfo personalInfoDTOToPersonalInfo(PersonalInfoDTO personalInfoDTO);

    CertificatesQualificationDTO certificatesQualificationToCertificatesQualificationDTO(CertificatesQualification certificatesQualification);
    CertificatesQualification certificatesQualificationDTOToCertificatesQualification(CertificatesQualificationDTO certificatesQualificationDTO);

    ContactDTO contactToContactDTO(Contact contact);
    Contact contactDTOToContact(ContactDTO contactDTO);

    DocumentDTO documentToDocumentDTO(Document document);
    Document documentDTOToDocument(DocumentDTO documentDTO);

    List<DocumentDTO> documentToDocumentDTO(List<Document> document);
    List<Document> documentDTOToDocument(List<DocumentDTO> documentDTO);

    EducationDTO educationToEducationDTO(Education education);
    Education educationDTOToEducation(EducationDTO educationDTO);

    LanguageDTO languageToLanguageDTO(Language language);
    Language languageDTOToLanguage(LanguageDTO languageDTO);

    List<LanguageDTO> languageToLanguageDTO(List<Language>  language);
    List<Language> languageDTOToLanguage(List<LanguageDTO> languageDTO);

    SpecializationDTO specializationToSpecializationDTO(Specialization specialization);
    Specialization specializationDTOToSpecialization(SpecializationDTO specializationDTO);

    @Mapping(target="startDate", source="workExperience.startData", dateFormat = "yyyy.MM.dd")
    @Mapping(target="endDate", source="workExperience.endData", dateFormat = "yyyy.MM.dd")
    WorkExperienceDTO workExperienceToWorkExperienceDTO(WorkExperience workExperience);
    @Mapping(target="startData", source="workExperienceDTO.startDate", dateFormat = "yyyy.MM.dd")
    @Mapping(target="endData", source="workExperienceDTO.startDate", dateFormat = "yyyy.MM.dd")
    WorkExperience workExperienceDTOToWorkExperience(WorkExperienceDTO workExperienceDTO);

    default byte[] stringToBytes(String string) {
        return string != null ? string.getBytes() : null;
    }
    default String bytesToString(byte[] bytes) {
        return bytes != null ? new String(bytes, StandardCharsets.UTF_8) : null;
    }
}
