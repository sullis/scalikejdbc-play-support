/*
 * Copyright 2015 scalikejdbc.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package scalikejdbc

import play.api.mvc.{ Action, ActionBuilder }

trait DBActionBuilders {

  protected implicit lazy val connectionPoolContext: ConnectionPoolContext = NoConnectionPoolContext

  def NamedDBAction(name: Any): ActionBuilder[DBSessionRequest] = Action.andThen(new DBActionFunction(name))

  lazy val DBAction: ActionBuilder[DBSessionRequest] = NamedDBAction('default)

  def NamedDBTxAction(name: Any): ActionBuilder[DBSessionRequest] = Action.andThen(new DBTxActionFunction(name))

  lazy val DBTxAction: ActionBuilder[DBSessionRequest] = NamedDBTxAction('default)

}

object DBActionBuilders extends DBActionBuilders