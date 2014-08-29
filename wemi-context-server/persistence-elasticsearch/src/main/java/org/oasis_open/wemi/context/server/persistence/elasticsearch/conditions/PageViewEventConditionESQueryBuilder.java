package org.oasis_open.wemi.context.server.persistence.elasticsearch.conditions;

import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.oasis_open.wemi.context.server.api.conditions.Condition;

import java.util.ArrayList;
import java.util.List;

/**
* Created by toto on 27/06/14.
*/
public class PageViewEventConditionESQueryBuilder implements ESQueryBuilder {

    public PageViewEventConditionESQueryBuilder() {
    }

    public FilterBuilder buildFilter(Condition condition, ConditionESQueryBuilderDispatcher dispatcher) {
        List<FilterBuilder> l = new ArrayList<FilterBuilder>();
        l.add(FilterBuilders.termFilter("eventType", "view"));
        if (condition.getParameterValues().get("url") != null && !"".equals(condition.getParameterValues().get("url"))) {
            l.add(FilterBuilders.termFilter("properties.page.pageInfo.destinationURL", (String) condition.getParameterValues().get("url")));
        }
        if (l.size() > 1) {
            return FilterBuilders.andFilter(l.toArray(new FilterBuilder[l.size()]));
        } else {
            return l.get(0);
        }
    }
}
