import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat, getSortState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IOrderDelivery } from 'app/shared/model/order-delivery.model';
import { getEntities } from './order-delivery.reducer';

export const OrderDelivery = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(location, ITEMS_PER_PAGE, 'id'), location.search)
  );

  const orderDeliveryList = useAppSelector(state => state.orderDelivery.entities);
  const loading = useAppSelector(state => state.orderDelivery.loading);
  const totalItems = useAppSelector(state => state.orderDelivery.totalItems);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      })
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    if (location.search !== endURL) {
      navigate(`${location.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  useEffect(() => {
    const params = new URLSearchParams(location.search);
    const page = params.get('page');
    const sort = params.get(SORT);
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [location.search]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const handleSyncList = () => {
    sortEntities();
  };

  return (
    <div>
      <h2 id="order-delivery-heading" data-cy="OrderDeliveryHeading">
        Order Deliveries
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh list
          </Button>
          <Link to="/order-delivery/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Order Delivery
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {orderDeliveryList && orderDeliveryList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  ID <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('deliveryDate')}>
                  Delivery Date <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('deliveryCharge')}>
                  Delivery Charge <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('shippingStatus')}>
                  Shipping Status <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Order <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Shipping Address <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Delivery Manager <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  Delivery Boy <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {orderDeliveryList.map((orderDelivery, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/order-delivery/${orderDelivery.id}`} color="link" size="sm">
                      {orderDelivery.id}
                    </Button>
                  </td>
                  <td>
                    {orderDelivery.deliveryDate ? (
                      <TextFormat type="date" value={orderDelivery.deliveryDate} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{orderDelivery.deliveryCharge}</td>
                  <td>{orderDelivery.shippingStatus}</td>
                  <td>{orderDelivery.order ? <Link to={`/orders/${orderDelivery.order.id}`}>{orderDelivery.order.id}</Link> : ''}</td>
                  <td>
                    {orderDelivery.shippingAddress ? (
                      <Link to={`/shipping-details/${orderDelivery.shippingAddress.id}`}>{orderDelivery.shippingAddress.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {orderDelivery.deliveryManager ? (
                      <Link to={`/daraz-users/${orderDelivery.deliveryManager.id}`}>{orderDelivery.deliveryManager.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {orderDelivery.deliveryBoy ? (
                      <Link to={`/daraz-users/${orderDelivery.deliveryBoy.id}`}>{orderDelivery.deliveryBoy.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/order-delivery/${orderDelivery.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/order-delivery/${orderDelivery.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/order-delivery/${orderDelivery.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Order Deliveries found</div>
        )}
      </div>
      {totalItems ? (
        <div className={orderDeliveryList && orderDeliveryList.length > 0 ? '' : 'd-none'}>
          <div className="justify-content-center d-flex">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} />
          </div>
          <div className="justify-content-center d-flex">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={totalItems}
            />
          </div>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

export default OrderDelivery;
