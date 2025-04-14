package cdm.base.staticdata.asset.common.tabulator;

import cdm.base.staticdata.asset.common.CollateralIssuerType;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(CollateralIssuerTypeTypeTabulator.Impl.class)
public interface CollateralIssuerTypeTypeTabulator extends Tabulator<CollateralIssuerType> {
	@Singleton
	class Impl implements CollateralIssuerTypeTypeTabulator {
		private final Field issuerTypeField;
		private final Field supraNationalTypeField;
		private final Field quasiGovernmentTypeField;
		private final Field regionalGovernmentTypeField;
		private final Field specialPurposeVehicleTypeField;

		private final QuasiGovernmentIssuerTypeTypeTabulator quasiGovernmentIssuerTypeTypeTabulator;
		private final RegionalGovernmentIssuerTypeTypeTabulator regionalGovernmentIssuerTypeTypeTabulator;
		private final SpecialPurposeVehicleIssuerTypeTypeTabulator specialPurposeVehicleIssuerTypeTypeTabulator;

		@Inject
		public Impl(QuasiGovernmentIssuerTypeTypeTabulator quasiGovernmentIssuerTypeTypeTabulator, RegionalGovernmentIssuerTypeTypeTabulator regionalGovernmentIssuerTypeTypeTabulator, SpecialPurposeVehicleIssuerTypeTypeTabulator specialPurposeVehicleIssuerTypeTypeTabulator) {
			this.quasiGovernmentIssuerTypeTypeTabulator = quasiGovernmentIssuerTypeTypeTabulator;
			this.regionalGovernmentIssuerTypeTypeTabulator = regionalGovernmentIssuerTypeTypeTabulator;
			this.specialPurposeVehicleIssuerTypeTypeTabulator = specialPurposeVehicleIssuerTypeTypeTabulator;
			this.issuerTypeField = new FieldImpl(
				"issuerType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.supraNationalTypeField = new FieldImpl(
				"supraNationalType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.quasiGovernmentTypeField = new FieldImpl(
				"quasiGovernmentType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.regionalGovernmentTypeField = new FieldImpl(
				"regionalGovernmentType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.specialPurposeVehicleTypeField = new FieldImpl(
				"specialPurposeVehicleType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CollateralIssuerType input) {
			FieldValue issuerType = new FieldValueImpl(issuerTypeField, Optional.ofNullable(input.getIssuerType()));
			FieldValue supraNationalType = new FieldValueImpl(supraNationalTypeField, Optional.ofNullable(input.getSupraNationalType()));
			FieldValue quasiGovernmentType = Optional.ofNullable(input.getQuasiGovernmentType())
				.map(x -> new NestedFieldValueImpl(quasiGovernmentTypeField, Optional.of(quasiGovernmentIssuerTypeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(quasiGovernmentTypeField, Optional.empty()));
			FieldValue regionalGovernmentType = Optional.ofNullable(input.getRegionalGovernmentType())
				.map(x -> new NestedFieldValueImpl(regionalGovernmentTypeField, Optional.of(regionalGovernmentIssuerTypeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(regionalGovernmentTypeField, Optional.empty()));
			FieldValue specialPurposeVehicleType = Optional.ofNullable(input.getSpecialPurposeVehicleType())
				.map(x -> new NestedFieldValueImpl(specialPurposeVehicleTypeField, Optional.of(specialPurposeVehicleIssuerTypeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(specialPurposeVehicleTypeField, Optional.empty()));
			return Arrays.asList(
				issuerType,
				supraNationalType,
				quasiGovernmentType,
				regionalGovernmentType,
				specialPurposeVehicleType
			);
		}
	}
}
