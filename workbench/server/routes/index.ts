/*
 *   Copyright 2020 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License").
 *   You may not use this file except in compliance with the License.
 *   A copy of the License is located at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   or in the "license" file accompanying this file. This file is distributed
 *   on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 *   express or implied. See the License for the specific language governing
 *   permissions and limitations under the License.
 */

import { ILegacyClusterClient, IRouter } from '../../../../src/core/server';
import registerTranslateRoute from './translate';
import registerQueryRoute from './query';
import TranslateService from '../services/TranslateService';
import QueryService from '../services/QueryService';


export default function (router: IRouter, client: ILegacyClusterClient) {
  // router.get(
  //   {
  //     path: '/api/workbench/example',
  //     validate: false,
  //   },
  //   async (context, request, response) => {
  //     return response.ok({
  //       body: {
  //         time: new Date().toISOString(),
  //       },
  //     });
  //   }
  // );
  const translateService = new TranslateService(client);
  registerTranslateRoute(router, translateService);

  const queryService = new QueryService(client);
  registerQueryRoute(router, queryService);
}
