package com.cysds.controller;

import com.cysds.dao.ISCDao;
import com.cysds.dao.po.SC;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/sc")
@CrossOrigin
public class SCController {

    @Resource
    private ISCDao scDao;

    @GetMapping
    public List<SC> getAll() {
        return scDao.queryAllSC();
    }

    @GetMapping("/student/{sno}")
    public List<SC> getBySno(@PathVariable String sno) {
        return scDao.querySCBySno(sno);
    }

    @GetMapping("/course/{cno}")
    public List<SC> getByCno(@PathVariable String cno) {
        return scDao.querySCByCno(cno);
    }

    @PostMapping
    public String add(@RequestBody SC sc) {
        scDao.addSC(sc);
        return "OK";
    }

    @DeleteMapping("/{sno}/{cno}")
    public String delete(@PathVariable String sno, @PathVariable String cno) {
        scDao.deleteSC(sno, cno);
        return "Deleted";
    }

    @DeleteMapping("/student/{sno}")
    public String deleteBySno(@PathVariable String sno) {
        scDao.deleteSCBySno(sno);
        return "Deleted";
    }

    @DeleteMapping("/course/{cno}")
    public String deleteByCno(@PathVariable String cno) {
        scDao.deleteSCByCno(cno);
        return "Deleted";
    }

    @PutMapping
    public String update(@RequestBody SC sc) {
        scDao.updateSC(sc);
        return "Updated";
    }
} 