package spring.devrep.sante.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.devrep.sante.entities.Dispo;
import spring.devrep.sante.entities.Pro;
import spring.devrep.sante.entities.RDV;
import spring.devrep.sante.repository.DispoInsertRepository;
import spring.devrep.sante.repository.DispoRepository;
import spring.devrep.sante.repository.PersonInsertRepository;
import spring.devrep.sante.repository.ProRepository;
import spring.devrep.sante.repository.RDVInsertRepository;
import spring.devrep.sante.repository.RDVRepository;

@Controller
public class SanteController {

    @Autowired
    private ProRepository prorep;
    @Autowired
    private PersonInsertRepository pir;
    @Autowired
    private RDVRepository rdvrep;
    @Autowired
    private RDVInsertRepository rdvinsert;
    @Autowired
    private DispoRepository disporep;
    @Autowired
    private DispoInsertRepository dispoinsert;

    // Pro pro = prorep.getOne(id);

    @RequestMapping("/")
    public String acceuil() {
        return "home.html";
    }

    @RequestMapping("/sante")
    public String acceuil2() {
        return "home.html";
    }

    @RequestMapping("/login")
    public String log() {
        return "login.html";
    }

    @RequestMapping("sante/login")
    public String login() {
        return "login.html";
    }

    @RequestMapping("sante/lp")
    public String listepro(Model model, Authentication authentication,
            @RequestParam(name = "keyword", defaultValue = "") String designation) {
        List<Pro> pros = prorep.findByNomContainsAndVisitor(designation, false);
        model.addAttribute("listPros", pros);
        if (authentication != null
                && authentication.getAuthorities().contains(new SimpleGrantedAuthority((String) "VISITOR"))) {
            return "visitors.html";
        } else {
            return "pros.html";
        }
    }

    @RequestMapping("sante/lp2")
    public String listepro2(Model model, Authentication authentication,
            @RequestParam(name = "keyword", defaultValue = "") String designation) {
        List<Pro> pros = prorep.findByMetierContainsAndVisitor(designation, false);
        model.addAttribute("listPros", pros);
        if (authentication != null
                && authentication.getAuthorities().contains(new SimpleGrantedAuthority((String) "VISITOR"))) {
            return "visitors.html";
        } else {
            return "pros.html";
        }
    }

    @RequestMapping("sante/addvisitor")
    public String addVisitor() {
        return "entry2.html";
    }

    @RequestMapping("sante/addpro")
    public String addPro() {
        return "entry.html";
    }

    @RequestMapping("sante/newpro")
    public String newpro(Model model, @RequestParam(name = "nom") String nom,
            @RequestParam(name = "prenom") String prenom,
            @RequestParam(name = "mail") String mail, @RequestParam(name = "mdp") String mdp) {
        if (mail != "" && mdp != "") {
            if (prorep.findByMail(mail) != null) {
                model.addAttribute("mailInUse", mail);
                return "entry.html";
            }
            pir.insertWithEntityManager(new Pro(nom, prenom, mail, mdp, false));
            return "home.html";
        } else {
            return "entry.html";
        }
    }

    @RequestMapping("sante/newvisitor")
    public String newvisitor(Model model, @RequestParam(name = "nom") String nom,
            @RequestParam(name = "prenom") String prenom,
            @RequestParam(name = "mail") String mail, @RequestParam(name = "mdp") String mdp) {
        if (mail != "" && mdp != "") {
            if (prorep.findByMail(mail) != null) {
                model.addAttribute("mailInUse", mail);
                return "entry2.html";
            }
            pir.insertWithEntityManager(new Pro(nom, prenom, mail, mdp, true));
            return "home.html";
        } else {
            return "entry2.html";
        }
    }

    @RequestMapping("sante/delete")
    public String delpro(@RequestParam(name = "id") Long id) {
        prorep.deleteById(id);
        return "pros.html";
    }

    @PreAuthorize("hasAuthority('PRO') or hasAuthority('VISITOR')")
    @RequestMapping("sante/mesInfo")
    public String userSplit(Model model, Authentication authentication) {
        model.addAttribute("pro", prorep.findByMail(authentication.getName()));
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority((String) "PRO"))) {
            return "mesInfoPro.html";
        } else {
            return "mesInfoVisitor.html";
        }
    }

    @PreAuthorize("hasAuthority('PRO')")
    @RequestMapping("sante/mesInfoPro")
    public String infoPro(Model model, Authentication authentication) {
        model.addAttribute("pro", prorep.findByMail(authentication.getName()));
        return "mesInfoPro.html";
    }

    @PreAuthorize("hasAuthority('VISITOR')")
    @RequestMapping("sante/mesInfoVisitor")
    public String infoVisitor(Model model, Authentication authentication) {
        model.addAttribute("pro", prorep.findByMail(authentication.getName()));
        return "mesInfoVisitor.html";
    }

    @PreAuthorize("hasAuthority('PRO')")
    @RequestMapping("sante/viewrdv")
    public String rdv(Model model, Authentication authentication) {
        Pro pro = prorep.findByMail(authentication.getName());
        model.addAttribute("listrdv", rdvrep.findByPro_id(pro.getId()));
        return "gestrdv.html";
    }

    @PreAuthorize("hasAuthority('PRO')")
    @RequestMapping("sante/viewdispo")
    public String dispo(Model model, Authentication authentication) {
        Pro pro = prorep.findByMail(authentication.getName());
        model.addAttribute("listdispo", disporep.findByPro_id(pro.getId()));
        return "gestdispo.html";
    }

    @PreAuthorize("hasAuthority('PRO')")
    @RequestMapping("sante/deletedispo")
    public String deldispo(Model model, Authentication authentication, @RequestParam(name = "id") Long id) {
        disporep.deleteById(id);
        Pro pro = prorep.findByMail(authentication.getName());
        model.addAttribute("listdispo", disporep.findByPro_id(pro.getId()));
        return "gestdispo.html";
    }

    @PreAuthorize("hasAuthority('PRO')")
    @RequestMapping("sante/adddispo")
    public String adddispo(Model model, Authentication authentication,
            @RequestParam(name = "heure", defaultValue = "") String heure,
            @RequestParam(name = "jour", defaultValue = "") String jour) {

        Pro pro = prorep.findByMail(authentication.getName());
        if (heure != "" && jour != "") {
            Dispo dispo = new Dispo(pro, jour, heure);
            dispoinsert.insertWithEntityManager(dispo);
        }
        model.addAttribute("listdispo", disporep.findByPro_id(pro.getId()));
        return "gestdispo.html";
    }

    @PreAuthorize("hasAuthority('PRO')")
    @RequestMapping("sante/deleterdvpro")
    public String deleterdvpro(Model model, Authentication authentication, @RequestParam(name = "id") Long id) {
        rdvrep.deleteById(id);
        Pro pro = prorep.findByMail(authentication.getName());
        model.addAttribute("listrdv", rdvrep.findByPro_id(pro.getId()));
        return "gestrdv.html";
    }

    @PreAuthorize("hasAuthority('VISITOR')")
    @RequestMapping("sante/viewrdvisitor")
    public String rdvisitor(Model model, Authentication authentication) {
        Pro pro = prorep.findByMail(authentication.getName());
        model.addAttribute("listrdv", rdvrep.findByPatient_id(pro.getId()));
        return "gestrdvVisitor.html";
    }

    @PreAuthorize("hasAuthority('VISITOR')")
    @RequestMapping("sante/deleterdv")
    public String deleterdv(Model model, Authentication authentication, @RequestParam(name = "id") Long id) {
        rdvrep.deleteById(id);
        Pro pro = prorep.findByMail(authentication.getName());
        model.addAttribute("listrdv", rdvrep.findByPatient_id(pro.getId()));
        return "gestrdvVisitor.html";
    }

    @PreAuthorize("hasAuthority('VISITOR')")
    @RequestMapping("sante/findrdv")
    public String findrdv(Model model, Authentication authentication, @RequestParam(name = "id") Long id) {
        model.addAttribute("listdispo", disporep.findByPro_id(id));
        return "takerdv.html";
    }

    @PreAuthorize("hasAuthority('VISITOR')")
    @RequestMapping("sante/confirmrdv")
    public String confirmrdv(Model model, Authentication authentication, @RequestParam(name = "iddispo") Long iddispo,
            @RequestParam(name = "idpro") Long idpro,
            @RequestParam(name = "heure") String heure, @RequestParam(name = "jour") String jour) {
        disporep.deleteById(iddispo);
        Pro patient = prorep.findByMail(authentication.getName());
        rdvinsert.insertWithEntityManager(new RDV(prorep.getReferenceById(idpro), patient, jour, heure));
        model.addAttribute("listrdv", rdvrep.findByPatient_id(patient.getId()));
        return "gestrdvVisitor.html";
    }

    @PreAuthorize("hasAuthority('VISITOR')")
    @RequestMapping("sante/updateinfoVisitor")
    public String updateinfoVisitor(Model model, Authentication authentication) {
        Pro patient = prorep.findByMail(authentication.getName());
        model.addAttribute("pro", patient);
        return "changeinfoVisitor.html";
    }

    @PreAuthorize("hasAuthority('VISITOR')")
    @RequestMapping("sante/changeinfoVisitor")
    public String changeinfoVisitor(Model model, Authentication authentication, @RequestParam(name = "nom") String nom,
            @RequestParam(name = "prenom") String prenom) {
        Pro patient = prorep.findByMail(authentication.getName());
        if (!nom.isBlank()) {
            patient.setNom(nom);
        }
        if (!prenom.isBlank()) {
            patient.setPrenom(prenom);
        }
        prorep.save(patient);
        model.addAttribute("pro", patient);
        return "mesInfoVisitor.html";
    }

    @PreAuthorize("hasAuthority('PRO')")
    @RequestMapping("sante/updateinfoPro")
    public String updateinfoPro(Model model, Authentication authentication) {
        Pro pro = prorep.findByMail(authentication.getName());
        model.addAttribute("pro", pro);
        return "changeinfoPro.html";
    }

    @PreAuthorize("hasAuthority('PRO')")
    @RequestMapping("sante/changeinfoPro")
    public String changeinfoPro(Model model, Authentication authentication, @RequestParam(name = "nom") String nom,
            @RequestParam(name = "prenom") String prenom, @RequestParam(name = "metier", defaultValue = "") String metier,
            @RequestParam(name = "adresse") String adresse, @RequestParam(name = "horaire") String horaire,
            @RequestParam(name = "tel") String tel) {
        Pro pro = prorep.findByMail(authentication.getName());
        if (!nom.isBlank()) {
            pro.setNom(nom);
        }
        if (!prenom.isBlank()) {
            pro.setPrenom(prenom);
        }
        if (!metier.isBlank()) {
            pro.setMetier(metier);
        }
        if (!adresse.isBlank()) {
            pro.setAdresse(adresse);
        }
        if (!horaire.isBlank()) {
            pro.setHoraire(horaire);
        }
        if (!tel.isBlank()) {
            pro.setTel(tel);
        }
        prorep.save(pro);
        model.addAttribute("pro", pro);
        return "mesInfoPro.html";
    }

}
