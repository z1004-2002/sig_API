package com._gi.sig.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._gi.sig.dto.ResultCandidat;
import com._gi.sig.dto.ResultLevel;
import com._gi.sig.dto.ResultRequest;
import com._gi.sig.dto.ResultResponse;
import com._gi.sig.models.Candidat;
import com._gi.sig.models.Result;
import com._gi.sig.repository.ResultatRepository;

@Service
public class ResultService {
    @Autowired
    private ResultatRepository resultatRepository;
    @Autowired
    private CandidatService candidatService;
    @Autowired
    private BureauService bureauService;

    public ResultResponse addResult(ResultRequest request, UUID bureauId, UUID candidatId) {
        List<Result> results = resultatRepository.findByBureauIdAndCandidatId(bureauId, candidatId);
        if (results.isEmpty()) {
            return toResusltResponse(
                    resultatRepository.save(
                            Result.builder()
                                    .bureauId(bureauId)
                                    .candidatId(candidatId)
                                    .nombreVoie(request.getNombreVoie())
                                    .build()));
        }
        Result r = results.get(0);
        r.setNombreVoie(request.getNombreVoie());
        return toResusltResponse(
                resultatRepository.save(r));

    }

    public List<ResultResponse> getAllResultsByBureau(UUID bureauId) {
        return resultatRepository.findByBureauId(bureauId).stream()
                .map(this::toResusltResponse)
                .toList();
    }

    public void deleteResult(UUID id) {
        Result r = resultatRepository.findById(id).orElse(null);
        if (r != null) {
            resultatRepository.delete(r);
        } else {
            throw new RuntimeException("Result not found");
        }
    }

    public List<ResultLevel> resultByArrondissement() {
        List<ResultCandidat> candidats = candidatService.getAllCandidat().stream()
                .map(this::toResultCandidat)
                .toList();
        List<ResultLevel> resultLevels = new ArrayList<>();
        List<ResultResponse> results = resultatRepository.findAll().stream()
                .map(this::toResusltResponse)
                .toList();

        for (ResultResponse r : results) {
            if (isInResultLevel(resultLevels, r.getBureau().getArrondisssement())) {
                resultLevels.stream().forEach(
                        rl -> {
                            if (rl.getName().equals(r.getBureau().getArrondisssement())) {
                                rl.getResultCandidat().stream().forEach(
                                        rc -> {
                                            if (rc.getIdCandidat().equals(r.getCandidat().getId())) {
                                                rc.setNombreVoie(rc.getNombreVoie() + r.getNombreVoie());
                                                rl.setTotalElecteur(rl.getTotalElecteur() + r.getBureau().getNbreElecteurs());
                                            }
                                        });
                            }
                        });
            } else {
                ResultLevel rl = ResultLevel.builder()
                        .level("arrondissement")
                        .name(r.getBureau().getArrondisssement())
                        .resultCandidat(
                                new ArrayList<>(candidats))
                        .build();
                rl.getResultCandidat().stream().forEach(
                        rc -> {
                            if (rc.getIdCandidat().equals(r.getCandidat().getId())) {
                                rc.setNombreVoie(rc.getNombreVoie() + r.getNombreVoie());
                                rl.setTotalElecteur(rl.getTotalElecteur() + r.getBureau().getNbreElecteurs());
                            }
                        });
                resultLevels.add(rl);
            }
        }

        return resultLevels;
    }

    public List<ResultLevel> resultByDepartement() {
        List<ResultCandidat> candidats = candidatService.getAllCandidat().stream()
                .map(this::toResultCandidat)
                .toList();
        List<ResultLevel> resultLevels = new ArrayList<>();
        List<ResultResponse> results = resultatRepository.findAll().stream()
                .map(this::toResusltResponse)
                .toList();

        for (ResultResponse r : results) {
            if (isInResultLevel(resultLevels, r.getBureau().getDepartement())) {
                resultLevels.stream().forEach(
                        rl -> {
                            if (rl.getName().equals(r.getBureau().getDepartement())) {
                                rl.getResultCandidat().stream().forEach(
                                        rc -> {
                                            if (rc.getIdCandidat().equals(r.getCandidat().getId())) {
                                                rc.setNombreVoie(rc.getNombreVoie() + r.getNombreVoie());
                                                rl.setTotalElecteur(rl.getTotalElecteur() + r.getBureau().getNbreElecteurs());
                                            }
                                        });
                            }
                        });
            } else {
                ResultLevel rl = ResultLevel.builder()
                        .level("departement")
                        .name(r.getBureau().getDepartement())
                        .resultCandidat(
                                new ArrayList<>(candidats))
                        .build();
                rl.getResultCandidat().stream().forEach(
                        rc -> {
                            if (rc.getIdCandidat().equals(r.getCandidat().getId())) {
                                rc.setNombreVoie(rc.getNombreVoie() + r.getNombreVoie());
                                rl.setTotalElecteur(rl.getTotalElecteur() + r.getBureau().getNbreElecteurs());
                            }
                        });
                resultLevels.add(rl);
            }
        }

        return resultLevels;
    }

    public List<ResultLevel> resultByRegion() {
        List<ResultCandidat> candidats = candidatService.getAllCandidat().stream()
                .map(this::toResultCandidat)
                .toList();
        List<ResultLevel> resultLevels = new ArrayList<>();
        List<ResultResponse> results = resultatRepository.findAll().stream()
                .map(this::toResusltResponse)
                .toList();

        for (ResultResponse r : results) {
            if (isInResultLevel(resultLevels, r.getBureau().getRegion())) {
                resultLevels.stream().forEach(
                        rl -> {
                            if (rl.getName().equals(r.getBureau().getRegion())) {
                                rl.getResultCandidat().stream().forEach(
                                        rc -> {
                                            if (rc.getIdCandidat().equals(r.getCandidat().getId())) {
                                                rc.setNombreVoie(rc.getNombreVoie() + r.getNombreVoie());
                                                rl.setTotalElecteur(rl.getTotalElecteur() + r.getBureau().getNbreElecteurs());
                                            }
                                        });
                            }
                        });
            } else {
                ResultLevel rl = ResultLevel.builder()
                        .level("region")
                        .name(r.getBureau().getRegion())
                        .resultCandidat(
                                new ArrayList<>(candidats))
                        .build();
                rl.getResultCandidat().stream().forEach(
                        rc -> {
                            if (rc.getIdCandidat().equals(r.getCandidat().getId())) {
                                rc.setNombreVoie(rc.getNombreVoie() + r.getNombreVoie());
                                rl.setTotalElecteur(rl.getTotalElecteur() + r.getBureau().getNbreElecteurs());
                            }
                        });
                resultLevels.add(rl);
            }
        }

        return resultLevels;
    }

    public ResultLevel Allresult() {
        List<ResultCandidat> candidats = candidatService.getAllCandidat().stream()
                .map(this::toResultCandidat)
                .toList();
        ResultLevel resultLevel = ResultLevel.builder().resultCandidat(candidats).totalElecteur(0).build();
        List<ResultResponse> results = resultatRepository.findAll().stream()
                .map(this::toResusltResponse)
                .toList();

        for (ResultResponse r : results) {
            resultLevel.getResultCandidat().stream().forEach(
                    rc -> {
                        if (rc.getIdCandidat().equals(r.getCandidat().getId())) {
                            rc.setNombreVoie(rc.getNombreVoie() + r.getNombreVoie());
                        }
                    });
            resultLevel.setTotalElecteur(resultLevel.getTotalElecteur() + r.getNombreVoie());

        }
        return resultLevel;
    }

    private boolean isInResultLevel(List<ResultLevel> resultLevels, String levelName) {
        return resultLevels.stream()
                .anyMatch(r -> r.getName().equals(levelName));
    }

    private ResultCandidat toResultCandidat(Candidat c) {
        return ResultCandidat.builder()
                .idCandidat(c.getId())
                .nomCandidat(
                        c.getFirstName() + " " + c.getLastName())
                .parti(c.getParti())
                .couleur(c.getCouleur())
                .nombreVoie(0)
                .build();
    }

    private ResultResponse toResusltResponse(Result result) {
        return ResultResponse.builder()
                .id(result.getId())
                .bureau(
                        bureauService.getBureau(result.getBureauId()))
                .candidat(
                        candidatService.getCandidat(result.getCandidatId()))
                .nombreVoie(result.getNombreVoie())
                .build();

    }

}
