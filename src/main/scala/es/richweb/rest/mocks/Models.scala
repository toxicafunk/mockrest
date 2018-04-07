package es.richweb.rest.mocks

object Models {
  type Id = String

  sealed trait BaseEntity {
    val id: Id
  }
  case class ProvidedSection(section: String, hasLogisticMargin: Boolean)
  case class Provider(id: Id, sections: List[ProvidedSection]) extends BaseEntity
  case class Store(id: Id, tightFlowIndicator: String, logistic: Option[String]) extends BaseEntity
}
