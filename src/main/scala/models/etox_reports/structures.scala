package models.etox_reports

object structures {
  val structs = models.dataframe.DataFrame("/opt/etox_reports_2016_1/input/vitic_web/structures_web_2016_1.csv").filter(r => r("SMILES") != "")
  def exp = structs.toSDF("/opt/etox_reports_2016_1/input/vitic_web/structures_web_2016_1.sdf", smiles_field = "SMILES")
}

