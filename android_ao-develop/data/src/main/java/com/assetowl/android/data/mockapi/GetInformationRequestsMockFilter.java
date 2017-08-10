package com.assetowl.android.data.mockapi;

import android.content.Context;

import com.assetowl.android.data.mockapi.lib.MockFilter;
import com.google.gson.Gson;

import okhttp3.Response;

/**
 * Created by patrickyin on 16/5/17.
 */

public class GetInformationRequestsMockFilter extends MockFilter {
    public GetInformationRequestsMockFilter(Context context, Gson gson) {
        super(context, gson);
    }

    @Override
    public String getResponseString() {
        return "[\n" +
                "  {\n" +
                "    \"id\": 783,\n" +
                "    \"version\": 670,\n" +
                "    \"name\": \"New Drinks Layout - 21st March 2017\",\n" +
                "    \"description\": \"As per planogram effective 3/10/16\",\n" +
                "    \"state\": \"ACTIVE\",\n" +
                "    \"versionStatus\": \"ACTIVE\",\n" +
                "    \"updatedAt\": 1490060814000,\n" +
                "    \"type\": \"STORE\",\n" +
                "    \"createdByUserId\": 513,\n" +
                "    \"permissions\": {\n" +
                "      \"canEdit\": true,\n" +
                "      \"canAnswer\": true,\n" +
                "      \"canViewResults\": true,\n" +
                "      \"canShareResults\": true,\n" +
                "      \"canDelete\": true,\n" +
                "      \"canEnd\": true,\n" +
                "      \"canDuplicate\": true\n" +
                "    },\n" +
                "    \"stats\": {\n" +
                "      \"totalQuestions\": 6\n" +
                "    },\n" +
                "    \"status\": \"ACTIVE\",\n" +
                "    \"totalQuestions\": 6\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 787,\n" +
                "    \"version\": 771,\n" +
                "    \"name\": \"April 2017 - eServices Merchandising Focus\",\n" +
                "    \"description\": \"Visit Objectives:\\nMerchandise, check layouts & record details instore - including eServices, Communications and Gift Cards.\\nCheck for all current SIM stock, gift cards must be brought out and placed on stands.\\nPlace orders for any stock required (always check with franchisee before ordering).\",\n" +
                "    \"estimatedCompletionMins\": 5,\n" +
                "    \"state\": \"ACTIVE\",\n" +
                "    \"versionStatus\": \"DRAFT\",\n" +
                "    \"updatedAt\": 1491791049000,\n" +
                "    \"type\": \"STORE\",\n" +
                "    \"createdByUserId\": 690,\n" +
                "    \"dueDate\": 1493647198000,\n" +
                "    \"permissions\": {\n" +
                "      \"canEdit\": true,\n" +
                "      \"canAnswer\": true,\n" +
                "      \"canViewResults\": true,\n" +
                "      \"canShareResults\": true,\n" +
                "      \"canDelete\": true,\n" +
                "      \"canEnd\": true,\n" +
                "      \"canDuplicate\": true\n" +
                "    },\n" +
                "    \"stats\": {\n" +
                "      \"totalQuestions\": 11\n" +
                "    },\n" +
                "    \"status\": \"ACTIVE\",\n" +
                "    \"totalQuestions\": 11\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 789,\n" +
                "    \"version\": 676,\n" +
                "    \"name\": \"Confectionary Layout\",\n" +
                "    \"estimatedCompletionMins\": 5,\n" +
                "    \"state\": \"ACTIVE\",\n" +
                "    \"versionStatus\": \"ACTIVE\",\n" +
                "    \"updatedAt\": 1490135237000,\n" +
                "    \"type\": \"STORE\",\n" +
                "    \"createdByUserId\": 517,\n" +
                "    \"dueDate\": 1490360399000,\n" +
                "    \"permissions\": {\n" +
                "      \"canEdit\": true,\n" +
                "      \"canAnswer\": true,\n" +
                "      \"canViewResults\": true,\n" +
                "      \"canShareResults\": true,\n" +
                "      \"canDelete\": true,\n" +
                "      \"canEnd\": true,\n" +
                "      \"canDuplicate\": true\n" +
                "    },\n" +
                "    \"stats\": {\n" +
                "      \"totalQuestions\": 3\n" +
                "    },\n" +
                "    \"status\": \"ACTIVE\",\n" +
                "    \"totalQuestions\": 3\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 870,\n" +
                "    \"version\": 772,\n" +
                "    \"name\": \"Testing change of question\",\n" +
                "    \"state\": \"ACTIVE\",\n" +
                "    \"versionStatus\": \"ACTIVE\",\n" +
                "    \"updatedAt\": 1494380633000,\n" +
                "    \"type\": \"STORE\",\n" +
                "    \"createdByUserId\": 690,\n" +
                "    \"permissions\": {\n" +
                "      \"canEdit\": true,\n" +
                "      \"canAnswer\": true,\n" +
                "      \"canViewResults\": true,\n" +
                "      \"canShareResults\": true,\n" +
                "      \"canDelete\": true,\n" +
                "      \"canEnd\": true,\n" +
                "      \"canDuplicate\": true\n" +
                "    },\n" +
                "    \"stats\": {\n" +
                "      \"totalQuestions\": 1\n" +
                "    },\n" +
                "    \"status\": \"ACTIVE\",\n" +
                "    \"totalQuestions\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 883,\n" +
                "    \"version\": 788,\n" +
                "    \"name\": \"Associate Map question to Store without floor plan\",\n" +
                "    \"state\": \"ACTIVE\",\n" +
                "    \"versionStatus\": \"ACTIVE\",\n" +
                "    \"updatedAt\": 1494382112000,\n" +
                "    \"type\": \"STORE\",\n" +
                "    \"createdByUserId\": 478,\n" +
                "    \"permissions\": {\n" +
                "      \"canEdit\": true,\n" +
                "      \"canAnswer\": true,\n" +
                "      \"canViewResults\": true,\n" +
                "      \"canShareResults\": true,\n" +
                "      \"canDelete\": true,\n" +
                "      \"canEnd\": true,\n" +
                "      \"canDuplicate\": true\n" +
                "    },\n" +
                "    \"stats\": {\n" +
                "      \"totalQuestions\": 1\n" +
                "    },\n" +
                "    \"status\": \"ACTIVE\",\n" +
                "    \"totalQuestions\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 888,\n" +
                "    \"version\": 793,\n" +
                "    \"name\": \"DEV-2745 Permission edit response\",\n" +
                "    \"description\": \"Edit response on audit template\",\n" +
                "    \"state\": \"ACTIVE\",\n" +
                "    \"versionStatus\": \"ACTIVE\",\n" +
                "    \"updatedAt\": 1494458136000,\n" +
                "    \"type\": \"STORE\",\n" +
                "    \"createdByUserId\": 478,\n" +
                "    \"permissions\": {\n" +
                "      \"canEdit\": true,\n" +
                "      \"canAnswer\": true,\n" +
                "      \"canViewResults\": true,\n" +
                "      \"canShareResults\": true,\n" +
                "      \"canDelete\": true,\n" +
                "      \"canEnd\": true,\n" +
                "      \"canDuplicate\": true\n" +
                "    },\n" +
                "    \"stats\": {\n" +
                "      \"totalQuestions\": 1\n" +
                "    },\n" +
                "    \"status\": \"ACTIVE\",\n" +
                "    \"totalQuestions\": 1\n" +
                "  }\n" +
                "]";
    }

    @Override
    public Response getCustomResponse() {
        return null;
    }
}
