package cdm.product.template.validation.exists;

import cdm.base.math.metafields.ReferenceWithMetaNonNegativeQuantitySchedule;
import cdm.base.staticdata.party.PayerReceiver;
import cdm.observable.asset.metafields.ReferenceWithMetaObservable;
import cdm.observable.asset.metafields.ReferenceWithMetaPriceSchedule;
import cdm.product.asset.CorrelationReturnTerms;
import cdm.product.asset.DividendReturnTerms;
import cdm.product.asset.PriceReturnTerms;
import cdm.product.asset.VarianceReturnTerms;
import cdm.product.asset.VolatilityReturnTerms;
import cdm.product.template.PortfolioReturnTerms;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class PortfolioReturnTermsOnlyExistsValidator implements ValidatorWithArg<PortfolioReturnTerms, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends PortfolioReturnTerms> ValidationResult<PortfolioReturnTerms> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("priceReturnTerms", ExistenceChecker.isSet((PriceReturnTerms) o.getPriceReturnTerms()))
				.put("dividendReturnTerms", ExistenceChecker.isSet((DividendReturnTerms) o.getDividendReturnTerms()))
				.put("varianceReturnTerms", ExistenceChecker.isSet((VarianceReturnTerms) o.getVarianceReturnTerms()))
				.put("volatilityReturnTerms", ExistenceChecker.isSet((VolatilityReturnTerms) o.getVolatilityReturnTerms()))
				.put("correlationReturnTerms", ExistenceChecker.isSet((CorrelationReturnTerms) o.getCorrelationReturnTerms()))
				.put("payerReceiver", ExistenceChecker.isSet((PayerReceiver) o.getPayerReceiver()))
				.put("underlier", ExistenceChecker.isSet((ReferenceWithMetaObservable) o.getUnderlier()))
				.put("quantity", ExistenceChecker.isSet((ReferenceWithMetaNonNegativeQuantitySchedule) o.getQuantity()))
				.put("initialValuationPrice", ExistenceChecker.isSet((List<? extends ReferenceWithMetaPriceSchedule>) o.getInitialValuationPrice()))
				.put("interimValuationPrice", ExistenceChecker.isSet((List<? extends ReferenceWithMetaPriceSchedule>) o.getInterimValuationPrice()))
				.put("finalValuationPrice", ExistenceChecker.isSet((List<? extends ReferenceWithMetaPriceSchedule>) o.getFinalValuationPrice()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("PortfolioReturnTerms", ValidationType.ONLY_EXISTS, "PortfolioReturnTerms", path, "");
		}
		return failure("PortfolioReturnTerms", ValidationType.ONLY_EXISTS, "PortfolioReturnTerms", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
